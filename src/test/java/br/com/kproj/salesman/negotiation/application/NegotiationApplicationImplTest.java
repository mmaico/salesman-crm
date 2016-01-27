package br.com.kproj.salesman.negotiation.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.events.messages.BusinessProposalClosedWonMessage;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.negotiation.domain.proposal.BusinessProposalDomainService;
import br.com.kproj.salesman.negotiation.domain.proposal.CanChangeProposalStatusDomainService;
import br.com.kproj.salesman.register.application.prepare.PreUpdateItems;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

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

        final BusinessProposal businessProposal = new BusinessProposal();
        doNothing().when(service).checkBusinessRulesFor(businessProposal);
        given(repository.save(businessProposal)).willReturn(new BusinessProposal(1L));

        final BusinessProposal result = negotiationService.register(businessProposal);

        verify(paymentPrepare, times(1)).preUpdate(businessProposal);
        verify(productsPrepare, times(1)).preUpdate(businessProposal);
        verify(eventBus, times(1)).post(Mockito.any());
        assertThat(result.getId(), is(1L));

    }

    @Test(expected = ValidationException.class)
    public void shouldNotRegister() {
        final BusinessProposal businessProposal = new BusinessProposal();
        doThrow(ValidationException.class).when(service).checkBusinessRulesFor(businessProposal);
        given(repository.save(businessProposal)).willReturn(new BusinessProposal(1L));

        negotiationService.register(businessProposal);
    }

    @Test
    public void shouldUpdate() {

        final BusinessProposal businessProposal = new BusinessProposal(1L);
        businessProposal.setIntroduction("b");
        final BusinessProposal persisted = new BusinessProposal(1L);
        persisted.setIntroduction("a");
        given(repository.findOne(businessProposal.getId())).willReturn(persisted);

        doNothing().when(service).checkBusinessRulesFor(businessProposal);
        given(repository.save(businessProposal)).willReturn(businessProposal);

        final BusinessProposal result = negotiationService.register(businessProposal);
        assertThat(result.getId(), is(1L));
        assertThat(result.getIntroduction(), is("b"));
    }

    @Test(expected = ValidationException.class)
    public void shouldNotUpdate() {

        final BusinessProposal businessProposal = new BusinessProposal(1L);
        businessProposal.setIntroduction("b");
        final BusinessProposal persisted = new BusinessProposal(1L);
        persisted.setIntroduction("a");
        given(repository.findOne(businessProposal.getId())).willReturn(persisted);

        doThrow(ValidationException.class).when(service).checkBusinessRulesFor(businessProposal);

        negotiationService.register(businessProposal);
    }

    @Test
    public void shouldFindBusinessProposalByClient() {
        Person client = new Person();
        client.setId(1l);
        BusinessProposal proposal = mock(BusinessProposal.class);

        given(repository.findByClient(client)).willReturn(Lists.newArrayList(proposal));

        List<BusinessProposal> result = negotiationService.findByClient(client);

        assertThat(result.size(), is(1));
        assertThat(result.get(0), sameInstance(proposal));
    }

    @Test
    public void shouldReturnEmptyWhenClientWithoutIdONProposalByClient() {
        Person client = new Person();

        List<BusinessProposal> result = negotiationService.findByClient(client);

        assertThat(result.isEmpty(), is(Boolean.TRUE));
    }

    @Test
    public void shouldChangeTemperature() {
        User changeThat = mock(User.class);
        BusinessProposal proposal = mock(BusinessProposal.class);
        BusinessProposal proposalLoaded = mock(BusinessProposal.class);

        ProposalTemperature temperature = ProposalTemperature.HOT;

        given(proposal.getTemperature()).willReturn(ProposalTemperature.COLD);
        given(proposal.getId()).willReturn(1l);
        given(repository.findOne(1l)).willReturn(proposalLoaded);

        negotiationService.changeTemperature(proposal, changeThat, temperature);

        verify(proposalLoaded).setTemperature(ProposalTemperature.HOT);
    }

    @Test
    public void shouldDoNothingWhenStatusIsClosedWon() {
        User changeThat = mock(User.class);
        BusinessProposal proposal = mock(BusinessProposal.class);
        BusinessProposal proposalLoaded = mock(BusinessProposal.class);

        ProposalTemperature temperature = ProposalTemperature.HOT;

        given(proposal.getTemperature()).willReturn(ProposalTemperature.CLOSED_WON);
        given(proposal.getId()).willReturn(1l);
        given(repository.findOne(1l)).willReturn(proposalLoaded);

        negotiationService.changeTemperature(proposal, changeThat, temperature);

        verifyNoMoreInteractions(checkChange, eventBus);
    }

    @Test
    public void shouldChangeTemperatureToClosedWon() {
        User changeThat = mock(User.class);
        BusinessProposal proposal = mock(BusinessProposal.class);
        BusinessProposal proposalLoaded = mock(BusinessProposal.class);

        ProposalTemperature temperature = ProposalTemperature.CLOSED_WON;

        given(proposal.getId()).willReturn(1l);
        given(repository.findOne(1l)).willReturn(proposalLoaded);
        given(checkChange.isValidBusinessRulesFor(proposal, changeThat)).willReturn(Boolean.TRUE);

        negotiationService.changeTemperature(proposal, changeThat, temperature);

        verify(proposalLoaded).setTemperature(ProposalTemperature.CLOSED_WON);
        verify(eventBus).post(Mockito.any(BusinessProposalClosedWonMessage.class));
    }

}