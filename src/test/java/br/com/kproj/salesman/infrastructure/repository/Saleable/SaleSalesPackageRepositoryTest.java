package br.com.kproj.salesman.infrastructure.repository.Saleable;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SaleSalesPackageRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private SalesPackageRepository repository;


    @Test
    public void shouldFindPackageBySaleable() {
        SaleableUnit saleableUnit =  new SaleableUnit();
        saleableUnit.setId(5l);
        SalePackage salePackageUnit = new SalePackage();
        salePackageUnit.setId(4l);

        Optional<SalePackage> packageResult = repository.findBySaleable(salePackageUnit, saleableUnit);

        assertThat(packageResult.isPresent(), is(Boolean.TRUE));
        assertThat(packageResult.get().getId(), is(4l));
    }

    @Test
    public void shouldReturnPackage() {

        Optional<SalePackage> result = repository.getOne(4l);

        MatcherAssert.assertThat(result.isPresent(), is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnNotPresentWhenIdIsNotAPackage() {

        Optional<SalePackage> result = repository.getOne(1l);

        MatcherAssert.assertThat(result.isPresent(), is(Boolean.FALSE));
    }
}