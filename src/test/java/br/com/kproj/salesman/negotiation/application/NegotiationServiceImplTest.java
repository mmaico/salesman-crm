package br.com.kproj.salesman.negotiation.application;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.negotiation.domain.proposal.BusinessProposalDomainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class NegotiationServiceImplTest {

    @InjectMocks
    private NegotiationServiceImpl negotiationService;

    @Mock
    private BusinessProposalDomainService service;

    @Mock
    private BusinessProposalRepository repository;

    @Test
    public void shouldRegister() {

        final BusinessProposal businessProposal = new BusinessProposal();
        doNothing().when(service).checkBusinessRulesFor(businessProposal);
        given(repository.save(businessProposal)).willReturn(new BusinessProposal(1L));

        final BusinessProposal result = negotiationService.register(businessProposal);
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