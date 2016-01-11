package br.com.kproj.salesman.negotiation.application;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.negotiation.domain.proposal.BusinessProposalDomainService;
import br.com.kproj.salesman.register.application.prepare.PreUpdateItems;
import com.google.common.eventbus.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.hamcrest.CoreMatchers.is;
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

}