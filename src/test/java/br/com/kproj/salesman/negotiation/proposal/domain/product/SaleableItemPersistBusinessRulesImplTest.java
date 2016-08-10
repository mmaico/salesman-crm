package br.com.kproj.salesman.negotiation.proposal.domain.product;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.negotiation.proposal.domain.saleable.SaleableItemPersistBusinessRulesImpl;
import br.com.kproj.salesman.negotiation.proposal.domain.saleable.contract.PackageBusinessRules;
import br.com.kproj.salesman.negotiation.proposal.domain.saleable.contract.SaleablePersistBusinessRules;
import com.google.common.collect.Lists;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Set;

import static br.com.kproj.salesman.infrastructure.entity.builders.ProposalSaleableItemBuilder.create;
import static br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder.createSaleableUnit;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
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

    @Test
    public void shouldReturnFalseWhenNegativePrice() {
        Set<String> messageErrorsExpected = null;
        BusinessProposal businessProposal = getProposalStub();
        businessProposal.getSaleableItems().get(0).setPrice(new BigDecimal("-1"));

        given(saleablePersistBusinessRules.verifyRules(businessProposal.getSaleableItems().get(0).getSaleableUnit()))
                .willReturn(Boolean.TRUE);

        given(saleablePersistBusinessRules.verifyRules(businessProposal.getSaleableItems().get(1).getSaleableUnit()))
                .willReturn(Boolean.TRUE);

        try {
            rules.verifyRules(businessProposal);
        } catch(ValidationException e) {
            messageErrorsExpected = e.getErrors();
        }

        MatcherAssert.assertThat(messageErrorsExpected, hasSize(1));
        MatcherAssert.assertThat(messageErrorsExpected.contains("domain.item.wihtout.price"), is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseWhenInvalidOriginalPrice() {

        Set<String> messageErrorsExpected = null;
        BusinessProposal businessProposal = getProposalStub();
        businessProposal.getSaleableItems().get(0).setOriginalPrice(null);

        given(saleablePersistBusinessRules.verifyRules(businessProposal.getSaleableItems().get(0).getSaleableUnit()))
                .willReturn(Boolean.TRUE);

        given(saleablePersistBusinessRules.verifyRules(businessProposal.getSaleableItems().get(1).getSaleableUnit()))
                .willReturn(Boolean.TRUE);

        try {
            rules.verifyRules(businessProposal);
        } catch(ValidationException e) {
            messageErrorsExpected = e.getErrors();
        }

        MatcherAssert.assertThat(messageErrorsExpected, hasSize(1));
        MatcherAssert.assertThat(messageErrorsExpected.contains("proposal.item.invalid.original.price"), is(Boolean.TRUE));
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
        SaleableUnitEntity saleableUnitOne = createSaleableUnit(1l).build();
        SaleableUnitEntity saleableUnitTwo = createSaleableUnit(2l).build();
        BusinessProposal businessProposal = new BusinessProposal();

        ProposalSaleableItem proposalSaleableOne = create()
                .withSaleable(saleableUnitOne)
                .withQuantity(2)
                .withPrice(BigDecimal.TEN)
                .withOriginalPrice(BigDecimal.TEN).build();

        ProposalSaleableItem proposalSaleableTwo = create()
                .withSaleable(saleableUnitTwo)
                .withQuantity(1)
                .withPrice(BigDecimal.TEN)
                .withOriginalPrice(BigDecimal.TEN).build();

        businessProposal.setSaleableItems(Lists.newArrayList(proposalSaleableOne, proposalSaleableTwo));

        return businessProposal;
    }

}