package br.com.kproj.salesman.register.application.prepare;

import br.com.kproj.salesman.infrastructure.entity.builders.ProposalSaleableItemBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.infrastructure.repository.ProposalSaleableRepository;
import br.com.kproj.salesman.negotiation.proposal.application.NegotiationApplication;
import com.google.common.collect.Lists;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PrepareBusinessProposalEntityProductsToUpdateImplTest {

    @InjectMocks
    private PreUpdateProductsImpl prepare;

    @Mock
    private BusinessProposalRepository repository;

    @Mock
    private NegotiationApplication application;

    @Mock
    private ProposalSaleableRepository saleableRepository;


    @Test
    public void shouldUpdateWhenSalesItemIntersection() {
        BusinessProposalEntity proposalOldStub = getProposalOldStub();
        BusinessProposalEntity proposalNewStub = getProposalNewStub();


        given(application.getOne(1l)).willReturn(Optional.of(proposalOldStub));

        prepare.preUpdate(proposalNewStub);

        ProposalSaleableItem itemOne = proposalOldStub.getSaleableItems().get(0);
        ProposalSaleableItem itemTwo = proposalOldStub.getSaleableItems().get(1);


        MatcherAssert.assertThat(itemOne.getId(), is(2l));
        MatcherAssert.assertThat(itemOne.getPrice(), is(new BigDecimal(150)));
        MatcherAssert.assertThat(itemOne.getQuantity(), is(2));
        MatcherAssert.assertThat(itemOne.getSaleableUnit().getId(), is(3l));

        MatcherAssert.assertThat(itemTwo.getId(), is(5l));
        MatcherAssert.assertThat(itemTwo.getPrice(), is(new BigDecimal(250)));
        MatcherAssert.assertThat(itemTwo.getQuantity(), is(3));
        MatcherAssert.assertThat(itemTwo.getSaleableUnit().getId(), is(7l));
    }

    @Test
    public void shouldRemoveItemsWhenNotExistInNewProposal() {
        BusinessProposalEntity proposalOldStub = getProposalOldStub();
        BusinessProposalEntity proposalNewStub = getProposalNewStub();


        given(application.getOne(1l)).willReturn(Optional.of(proposalOldStub));

        prepare.preUpdate(proposalNewStub);

        ProposalSaleableItem itemOne = proposalOldStub.getSaleableItems().get(0);
        ProposalSaleableItem itemTwo = proposalOldStub.getSaleableItems().get(1);
        ProposalSaleableItem itemThree = proposalOldStub.getSaleableItems().get(2);

        MatcherAssert.assertThat(proposalOldStub.getSaleableItems().size(), is(3));
        MatcherAssert.assertThat(itemOne.getId(), is(2l));
        MatcherAssert.assertThat(itemTwo.getId(), is(5l));
        MatcherAssert.assertThat(itemThree.getId(), Matchers.nullValue());
    }

    @Test
    public void shouldAddItemOnOldProposalWhenExistInNewProposal() {
        BusinessProposalEntity proposalOldStub = getProposalOldStub();
        BusinessProposalEntity proposalNewStub = getProposalNewStub();


        given(application.getOne(1l)).willReturn(Optional.of(proposalOldStub));

        prepare.preUpdate(proposalNewStub);

        ProposalSaleableItem itemThree = proposalOldStub.getSaleableItems().get(2);

        MatcherAssert.assertThat(proposalOldStub.getSaleableItems().size(), is(3));

        MatcherAssert.assertThat(itemThree.getId(), Matchers.nullValue());
        MatcherAssert.assertThat(itemThree, Matchers.sameInstance(proposalNewStub.getSaleableItems().get(2)));
    }


    public BusinessProposalEntity getProposalOldStub() {
        BusinessProposalEntity proposalOld = new BusinessProposalEntity(1l);

        ProposalSaleableItem itemOne = ProposalSaleableItemBuilder
                    .createProposalSaleable(2l).withOriginalPrice(new BigDecimal("200"))
                    .withPrice(new BigDecimal("100"))
                    .withQuantity(4)
                    .withSaleable(new SaleableUnitEntity(3l)).build();

        ProposalSaleableItem itemTwo = ProposalSaleableItemBuilder
                .createProposalSaleable(5l).withOriginalPrice(new BigDecimal("400"))
                .withPrice(new BigDecimal("200"))
                .withQuantity(2)
                .withSaleable(new SaleableUnitEntity(7l)).build();

        ProposalSaleableItem itemThree = ProposalSaleableItemBuilder
                .createProposalSaleable(6l).withOriginalPrice(new BigDecimal("500"))
                .withPrice(new BigDecimal("250"))
                .withQuantity(5)
                .withSaleable(new SaleableUnitEntity(10l)).build();

        proposalOld.setSaleableItems(Lists.newArrayList(itemOne, itemTwo, itemThree));

        return proposalOld;
    }

    public BusinessProposalEntity getProposalNewStub() {
        BusinessProposalEntity proposalNew = new BusinessProposalEntity(1l);

        ProposalSaleableItem itemOne = ProposalSaleableItemBuilder
                .createProposalSaleable(2l).withOriginalPrice(new BigDecimal("300"))
                .withPrice(new BigDecimal("150"))
                .withQuantity(2)
                .withSaleable(new SaleableUnitEntity(3l)).build();

        ProposalSaleableItem itemTwo = ProposalSaleableItemBuilder
                .createProposalSaleable(5l).withOriginalPrice(new BigDecimal("450"))
                .withPrice(new BigDecimal("250"))
                .withQuantity(3)
                .withSaleable(new SaleableUnitEntity(7l)).build();

        ProposalSaleableItem itemThree = ProposalSaleableItemBuilder
                .createProposalSaleable(null).withOriginalPrice(new BigDecimal("1000"))
                .withPrice(new BigDecimal("750"))
                .withQuantity(1)
                .withSaleable(new SaleableUnitEntity(21l)).build();

        proposalNew.setSaleableItems(Lists.newArrayList(itemOne, itemTwo, itemThree));

        return proposalNew;
    }

}