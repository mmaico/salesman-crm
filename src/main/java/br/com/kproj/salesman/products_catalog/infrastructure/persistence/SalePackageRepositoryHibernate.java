package br.com.kproj.salesman.products_catalog.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SalePackage;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SalePackageRepository;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.SalePackageRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SalePackageRepositoryHibernate implements SalePackageRepository {

    @Autowired
    private SalePackageRepositorySpringData repository;


    @Override
    public Optional<SalePackage> findBySaleable(SalePackage salePackage, SaleableUnit saleable) {

        return null;
    }


    @Override
    public Page<SalePackage> findAll(Pageable page) {
        Page<SalePackageEntity> salePackages = repository.findAll(page);
        return null;
    }

    @Override
    public Optional<SalePackage> findOne(Long id) {
        SalePackageEntity result = repository.findOne(id);
        return null;
    }

    @Override
    public Optional<SalePackage> save(SalePackage entity) {
        return null;
    }
}
