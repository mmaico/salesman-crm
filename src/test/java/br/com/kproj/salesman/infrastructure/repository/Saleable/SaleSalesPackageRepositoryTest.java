package br.com.kproj.salesman.infrastructure.repository.Saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SaleSalesPackageRepositoryTest  {
//extends AbstractIntegrationTest
    @Autowired
    private SalesPackageRepository repository;


    @Test
    public void shouldFindPackageBySaleable() {
        SaleableUnitEntity saleableUnit =  new SaleableUnitEntity();
        saleableUnit.setId(5l);
        SalePackageEntity salePackageUnit = new SalePackageEntity();
        salePackageUnit.setId(4l);

        Optional<SalePackageEntity> packageResult = repository.findBySaleable(salePackageUnit, saleableUnit);

        assertThat(packageResult.isPresent(), is(Boolean.TRUE));
        assertThat(packageResult.get().getId(), is(4l));
    }

    @Test
    public void shouldReturnPackage() {

        Optional<SalePackageEntity> result = repository.getOne(4l);

        MatcherAssert.assertThat(result.isPresent(), is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnNotPresentWhenIdIsNotAPackage() {

        Optional<SalePackageEntity> result = repository.getOne(1l);

        MatcherAssert.assertThat(result.isPresent(), is(Boolean.FALSE));
    }
}