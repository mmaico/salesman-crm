package br.com.kproj.salesman.negotiation.proposal.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.events.messages.BusinessProposalClosedWonMessage;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.negotiation.proposal.domain.BusinessProposalDomainService;
import br.com.kproj.salesman.negotiation.proposal.domain.CanChangeProposalStatusDomainService;
import br.com.kproj.salesman.register.application.prepare.PreUpdateItems;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NegotiationApplicationImplTest {

    @InjectMocks
    private NegotiationApplicationImpl negotiationService;

    @Mock
    private BusinessProposalDomainService service;

    @Mock
    private BusinessProposalRepository repository;

    @Mock
    private PreUpdateItems paymentPrepare;

    @Mock
    private PreUpdateItems productsPrepare;

    @Mock
    private CanChangeProposalStatusDomainService checkChange;

    @Mock
    private EventBus eventBus;

    @Test
    public void shouldRegister() {

        final BusinessProposalEntity businessProposalEntity = new BusinessProposalEntity();
        doNothing().when(service).checkBusinessRulesFor(businessProposalEntity);
        given(repository.save(businessProposalEntity)).willReturn(new BusinessProposalEntity(1L));

        final BusinessProposalEntity result = negotiationService.register(businessProposalEntity);

        verify(paymentPrepare, times(1)).preUpdate(businessProposalEntity);
        verify(productsPrepare, times(1)).preUpdate(businessProposalEntity);
        verify(eventBus, times(1)).post(Mockito.any());
        assertThat(result.getId(), is(1L));

    }

    @Test(expected = ValidationException.class)
    public void shouldNotRegister() {
        final BusinessProposalEntity businessProposalEntity = new BusinessProposalEntity();
        doThrow(ValidationException.class).when(service).checkBusinessRulesFor(businessProposalEntity);
        given(repository.save(businessProposalEntity)).willReturn(new BusinessProposalEntity(1L));

        negotiationService.register(businessProposalEntity);
    }

    @Test
    public void shouldUpdate() {

        final BusinessProposalEntity businessProposalEntity = new BusinessProposalEntity(1L);
        businessProposalEntity.setIntroduction("b");
        final BusinessProposalEntity persisted = new BusinessProposalEntity(1L);
        persisted.setIntroduction("a");
        given(repository.findOne(businessProposalEntity.getId())).willReturn(persisted);

        doNothing().when(service).checkBusinessRulesFor(businessProposalEntity);
        given(repository.save(businessProposalEntity)).willReturn(businessProposalEntity);

        final BusinessProposalEntity result = negotiationService.register(businessProposalEntity);
        assertThat(result.getId(), is(1L));
        assertThat(result.getIntroduction(), is("b"));
    }

    @Test(expected = ValidationException.class)
    public void shouldNotUpdate() {

        final BusinessProposalEntity businessProposalEntity = new BusinessProposalEntity(1L);
        businessProposalEntity.setIntroduction("b");
        final BusinessProposalEntity persisted = new BusinessProposalEntity(1L);
        persisted.setIntroduction("a");
        given(repository.findOne(businessProposalEntity.getId())).willReturn(persisted);

        doThrow(ValidationException.class).when(service).checkBusinessRulesFor(businessProposalEntity);

        negotiationService.register(businessProposalEntity);
    }

    @Test
    public void shouldFindBusinessProposalByClient() {
        Person client = new Person();
        client.setId(1l);
        BusinessProposalEntity proposal = mock(BusinessProposalEntity.class);

        given(repository.findByClient(client)).willReturn(Lists.newArrayList(proposal));

        List<BusinessProposalEntity> result = negotiationService.findByClient(client);

        assertThat(result.size(), is(1));
        assertThat(result.get(0), sameInstance(proposal));
    }

    @Test
    public void shouldReturnEmptyWhenClientWithoutIdONProposalByClient() {
        Person client = new Person();

        List<BusinessProposalEntity> result = negotiationService.findByClient(client);

        assertThat(result.isEmpty(), is(Boolean.TRUE));
    }

    @Test
    public void shouldChangeTemperature() {
        UserEntity changeThat = mock(UserEntity.class);
        BusinessProposalEntity proposal = mock(BusinessProposalEntity.class);
        BusinessProposalEntity proposalLoaded = mock(BusinessProposalEntity.class);

        ProposalTemperature temperature = ProposalTemperature.HOT;

        given(proposal.getTemperature()).willReturn(ProposalTemperature.COLD);
        given(proposal.getId()).willReturn(1l);
        given(repository.findOne(1l)).willReturn(proposalLoaded);

        negotiationService.changeTemperature(proposal, changeThat, temperature);

        verify(proposalLoaded).setTemperature(ProposalTemperature.HOT);
    }

    @Test
    public void shouldDoNothingWhenStatusIsClosedWon() {
        UserEntity changeThat = mock(UserEntity.class);
        BusinessProposalEntity proposal = mock(BusinessProposalEntity.class);
        BusinessProposalEntity proposalLoaded = mock(BusinessProposalEntity.class);

        ProposalTemperature temperature = ProposalTemperature.HOT;

        given(proposal.getTemperature()).willReturn(ProposalTemperature.CLOSED_WON);
        given(proposal.getId()).willReturn(1l);
        given(repository.findOne(1l)).willReturn(proposalLoaded);

        negotiationService.changeTemperature(proposal, changeThat, temperature);

        verifyNoMoreInteractions(checkChange, eventBus);
    }

    @Test
    public void shouldChangeTemperatureToClosedWon() {
        UserEntity changeThat = mock(UserEntity.class);
        BusinessProposalEntity proposal = mock(BusinessProposalEntity.class);
        BusinessProposalEntity proposalLoaded = mock(BusinessProposalEntity.class);

        ProposalTemperature temperature = ProposalTemperature.CLOSED_WON;

        given(proposal.getId()).willReturn(1l);
        given(repository.findOne(1l)).willReturn(proposalLoaded);
        given(checkChange.isValidBusinessRulesFor(proposal, changeThat)).willReturn(Boolean.TRUE);

        negotiationService.changeTemperature(proposal, changeThat, temperature);

        verify(proposalLoaded).setTemperature(ProposalTemperature.CLOSED_WON);
        verify(eventBus).post(Mockito.any(BusinessProposalClosedWonMessage.class));
    }

}