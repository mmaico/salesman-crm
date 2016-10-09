package br.com.kproj.salesman.products_catalog.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackageRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;


public class ProductRepositoryHibernateIT  {
//extends AbstractIntegrationTest
    @Autowired
    private SalePackageRepository repository;

    @Test
    public void should() {
        Page<SalePackage> all = repository.findAll(Pager.build().withPageSize(10));

        System.out.println(all);
    }
}