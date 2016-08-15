package br.com.kproj.salesman.products_catalog.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnitRepository;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.SaleableUnitRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SaleableUnitRepositoryHibernate implements SaleableUnitRepository {

    @Autowired
    private SaleableUnitRepositorySpringData repository;


    @Override
    public Page<SaleableUnit> findAll(Pageable page) {
        Page<SaleableUnitEntity> saleables = repository.findAll(page);
        return null;
    }

    @Override
    public Optional<SaleableUnit> findOne(Long id) {
        SaleableUnitEntity result = repository.findOne(id);
        return null;
    }

    @Override
    public Optional<SaleableUnit> save(SaleableUnit entity) {
        return null;
    }
}
