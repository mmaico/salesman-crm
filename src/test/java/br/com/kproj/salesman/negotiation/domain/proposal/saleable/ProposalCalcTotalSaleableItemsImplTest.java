package br.com.kproj.salesman.negotiation.domain.proposal.saleable;

import br.com.kproj.salesman.infrastructure.entity.builders.ProposalSaleableItemBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SalesPackageRepository;
import com.google.common.collect.Lists;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProposalCalcTotalSaleableItemsImplTest {

    @InjectMocks
    private ProposalCalcTotalSaleableItemsImpl proposalcalculator;

    @Mock
    private SalesPackageRepository salesPackageRepository;


    @Test
    public void shouldReturnCalcWithProductAndPackageWithPackageValueByProducts() {
        SalePackage salePackageDB = Mockito.mock(SalePackage.class);

        ProposalSaleableItem saleableItem = ProposalSaleableItemBuilder.create()
                .withPackage(new SalePackage(1l))
                .withSaleable(new SaleableUnit(2l))
                .withQuantity(2)
                .withPrice(new BigDecimal(100)).build();

        given(salePackageDB.calcPriceByProducts()).willReturn(Boolean.TRUE);
        given(salesPackageRepository.getOne(1l)).willReturn(Optional.of(salePackageDB));

        BigDecimal total = proposalcalculator.getTotal(Lists.newArrayList(saleableItem));


        MatcherAssert.assertThat(total, is(new BigDecimal("200")));
    }

    @Test
    public void shouldReturnCalcZeroWhenHaveProductAndPackageWithPackageValueByPackage() {
        SalePackage salePackageDB = Mockito.mock(SalePackage.class);

        ProposalSaleableItem saleableItem = ProposalSaleableItemBuilder.create()
                .withPackage(new SalePackage(1l))
                .withSaleable(new SaleableUnit(2l))
                .withQuantity(2)
                .withPrice(new BigDecimal(100)).build();

        given(salePackageDB.calcPriceByProducts()).willReturn(Boolean.FALSE);
        given(salesPackageRepository.getOne(1l)).willReturn(Optional.of(salePackageDB));

        BigDecimal total = proposalcalculator.getTotal(Lists.newArrayList(saleableItem));


        MatcherAssert.assertThat(total, is(BigDecimal.ZERO));
    }

    @Test
    public void shouldReturnCalcPackageValueWhenHaveOnlyPackageAndPackageValueByPackage() {
        SalePackage salePackageDB = Mockito.mock(SalePackage.class);

        ProposalSaleableItem saleableItem = ProposalSaleableItemBuilder.create()
                .withPackage(new SalePackage(1l))
                .withQuantity(2)
                .withPrice(new BigDecimal(200)).build();

        given(salePackageDB.calcPriceByProducts()).willReturn(Boolean.FALSE);
        given(salesPackageRepository.getOne(1l)).willReturn(Optional.of(salePackageDB));

        BigDecimal total = proposalcalculator.getTotal(Lists.newArrayList(saleableItem));


        MatcherAssert.assertThat(total, is(new BigDecimal("400")));
    }

    @Test
    public void shouldReturnCalcZeroWhenHavePackageWithPackageValueByProducts() {
        SalePackage salePackageDB = Mockito.mock(SalePackage.class);

        ProposalSaleableItem saleableItem = ProposalSaleableItemBuilder.create()
                .withPackage(new SalePackage(1l))
                .withQuantity(2)
                .withPrice(new BigDecimal(100)).build();

        given(salePackageDB.calcPriceByProducts()).willReturn(Boolean.TRUE);
        given(salesPackageRepository.getOne(1l)).willReturn(Optional.of(salePackageDB));

        BigDecimal total = proposalcalculator.getTotal(Lists.newArrayList(saleableItem));


        MatcherAssert.assertThat(total, is(BigDecimal.ZERO));
    }

    @Test
    public void shouldCalcWhenNotHavePackage() {

        ProposalSaleableItem saleableItem = ProposalSaleableItemBuilder.create()
                .withSaleable(new SaleableUnit(3l))
                .withQuantity(2)
                .withPrice(new BigDecimal(100)).build();


        BigDecimal total = proposalcalculator.getTotal(Lists.newArrayList(saleableItem));


        MatcherAssert.assertThat(total, is(new BigDecimal("200")));
    }

}