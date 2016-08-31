package br.com.kproj.salesman.negotiationold.proposal.domain.saleable;

import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static br.com.kproj.salesman.infrastructure.entity.builders.SaleableItemBuilder.create;
import static br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder.createSaleableUnit;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class SalePackageBusinessRulesImplTest {

    @InjectMocks
    private PackageBusinessRulesImpl rules;


//    @Test
//    public void shouldReturnTrueWhenPackageInItemExist() {
//
//        Boolean result = rules.verifyRules(getProposalStub());
//
//        assertThat(result, is(Boolean.TRUE));
//    }
//
//
//    @Test(expected = ValidationException.class)
//    public void shouldReturnFaseWhenPackageInItemNotHaveInList() {
//        List<ProposalSaleableItem> itemsStub = getProposalStub();
//        itemsStub.remove(2);
//
//        rules.verifyRules(itemsStub);
//    }
//
//    @Test(expected = ValidationException.class)
//    public void shouldThrowExceptionWhenPackageToPersistNotHaveAllPackageInReferencesSaleable() {
//        SalePackageEntity ipackage = new SalePackageEntity(3l);
//        SalePackageEntity salePackage2 = new SalePackageEntity(9l);
//        List<ProposalSaleableItem> items = getProposalStub();
//
//        ProposalSaleableItem newPackage = create()
//                .withPackage(salePackage2)
//                .withSaleable(null)
//                .withQuantity(1)
//                .withPrice(BigDecimal.ZERO).build();
//
//        items.add(newPackage);
//
//        items.get(0).setSalePackage(ipackage);
//
//        rules.verifyRules(items);
//    }

//    @Test
//    public void shouldReturnTrueWhenPackageInItemsAndPackageWithoutReference() {
//        List<ProposalSaleableItem> itemsStub = getProposalStub();
//        SalePackageEntity salePackageWithoutRefereceSaleable = new SalePackageEntity(9l);
//
//        ProposalSaleableItem proposalSaleablePackage = create()
//                .withPackage(salePackageWithoutRefereceSaleable)
//                .withSaleable(null)
//                .withQuantity(1)
//                .withPrice(BigDecimal.ZERO).build();
//
//        itemsStub.add(proposalSaleablePackage);
//
//        Boolean result = rules.verifyRules(itemsStub);
//
//        assertThat(result, is(Boolean.TRUE));
//    }
//
//
//    private List<ProposalSaleableItem> getProposalStub() {
//        SaleableUnitEntity saleableUnitOne = createSaleableUnit(1l).build();
//        SaleableUnitEntity saleableUnitTwo = createSaleableUnit(2l).build();
//        SalePackageEntity ipackage = new SalePackageEntity(2l);
//
//        ProposalSaleableItem proposalSaleableOne = create()
//                .withPackage(ipackage)
//                .withSaleable(saleableUnitOne)
//                .withQuantity(2)
//                .withPrice(BigDecimal.TEN).build();
//
//        ProposalSaleableItem proposalSaleableTwo = create()
//                .withPackage(ipackage)
//                .withSaleable(saleableUnitTwo)
//                .withQuantity(1)
//                .withPrice(BigDecimal.TEN).build();
//
//        ProposalSaleableItem proposalSaleablePackage = create()
//                .withPackage(ipackage)
//                .withSaleable(null)
//                .withQuantity(1)
//                .withPrice(BigDecimal.ZERO).build();
//
//        return newArrayList(proposalSaleableOne, proposalSaleableTwo, proposalSaleablePackage);
//    }

}