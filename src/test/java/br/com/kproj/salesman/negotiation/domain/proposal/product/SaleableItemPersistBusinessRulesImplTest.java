package br.com.kproj.salesman.negotiation.domain.proposal.product;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.negotiation.domain.proposal.saleable.SaleableItemPersistBusinessRulesImpl;
import br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract.PackageBusinessRules;
import br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract.SaleablePersistBusinessRules;
import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static br.com.kproj.salesman.infrastructure.entity.builders.ProposalSaleableItemBuilder.create;
import static br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder.createSaleableUnit;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SaleableItemPersistBusinessRulesImplTest {

    @InjectMocks
    private SaleableItemPersistBusinessRulesImpl rules;

    @Mock
    private SaleablePersistBusinessRules saleablePersistBusinessRules;

    @Mock
    private PackageBusinessRules packageBusinessRules;


    @Test
    public void shouldReturnTrueWhenValid() {
        BusinessProposal businessProposal = getProposalStub();

        given(saleablePersistBusinessRules.verifyRules(businessProposal.getSaleableItems().get(0).getSaleableUnit()))
                .willReturn(Boolean.TRUE);

        given(saleablePersistBusinessRules.verifyRules(businessProposal.getSaleableItems().get(1).getSaleableUnit()))
                .willReturn(Boolean.TRUE);

        Boolean result = rules.verifyRules(businessProposal);

        verify(packageBusinessRules).verifyRules(businessProposal.getSaleableItems());
        assertThat(result, Matchers.is(Boolean.TRUE));
    }

    @Test(expected = ValidationException.class)
    public void shouldReturnFalseWhenInvalidSaleable() {
        BusinessProposal businessProposal = getProposalStub();

        given(saleablePersistBusinessRules.verifyRules(businessProposal.getSaleableItems().get(0).getSaleableUnit()))
                .willReturn(Boolean.FALSE);

        given(saleablePersistBusinessRules.verifyRules(businessProposal.getSaleableItems().get(1).getSaleableUnit()))
                .willReturn(Boolean.TRUE);

        rules.verifyRules(businessProposal);

    }

    @Test(expected = ValidationException.class)
    public void shouldReturnFalseWhenNegativePrice() {
        BusinessProposal businessProposal = getProposalStub();
        businessProposal.getSaleableItems().get(0).setPrice(new BigDecimal("-1"));

        given(saleablePersistBusinessRules.verifyRules(businessProposal.getSaleableItems().get(0).getSaleableUnit()))
                .willReturn(Boolean.FALSE);

        given(saleablePersistBusinessRules.verifyRules(businessProposal.getSaleableItems().get(1).getSaleableUnit()))
                .willReturn(Boolean.FALSE);

        rules.verifyRules(businessProposal);
    }

    @Test(expected = ValidationException.class)
    public void shouldReturnFalseWhenQuantityLessThanZero() {
        BusinessProposal businessProposal = getProposalStub();
        businessProposal.getSaleableItems().get(0).setQuantity(-1);

        given(saleablePersistBusinessRules.verifyRules(businessProposal.getSaleableItems().get(0).getSaleableUnit()))
                .willReturn(Boolean.FALSE);

        given(saleablePersistBusinessRules.verifyRules(businessProposal.getSaleableItems().get(1).getSaleableUnit()))
                .willReturn(Boolean.FALSE);

        rules.verifyRules(businessProposal);
    }

    public BusinessProposal getProposalStub() {
        SaleableUnit saleableUnitOne = createSaleableUnit(1l).build();
        SaleableUnit saleableUnitTwo = createSaleableUnit(2l).build();
        BusinessProposal businessProposal = new BusinessProposal();

        ProposalSaleableItem proposalSaleableOne = create()
                .withSaleable(saleableUnitOne)
                .withQuantity(2)
                .withPrice(BigDecimal.TEN).build();

        ProposalSaleableItem proposalSaleableTwo = create()
                .withSaleable(saleableUnitTwo)
                .withQuantity(1)
                .withPrice(BigDecimal.TEN).build();

        businessProposal.setSaleableItems(Lists.newArrayList(proposalSaleableOne, proposalSaleableTwo));

        return businessProposal;
    }

}