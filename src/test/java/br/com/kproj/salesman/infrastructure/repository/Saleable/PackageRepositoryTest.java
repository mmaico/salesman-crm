package br.com.kproj.salesman.infrastructure.repository.Saleable;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.saleable.Package;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PackageRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private PackageRepository repository;


    @Test
    public void shouldFindPackageBySaleable() {
        SaleableUnit saleableUnit =  new SaleableUnit();
        saleableUnit.setId(5l);
        Package packageUnit = new Package();
        packageUnit.setId(4l);

        Optional<Package> packageResult = repository.findBySaleable(packageUnit, saleableUnit);

        assertThat(packageResult.isPresent(), is(Boolean.TRUE));
        assertThat(packageResult.get().getId(), is(4l));
    }
}