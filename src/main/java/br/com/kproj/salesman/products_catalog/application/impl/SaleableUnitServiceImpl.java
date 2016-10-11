package br.com.kproj.salesman.products_catalog.application.impl;


import br.com.kproj.salesman.products_catalog.application.SaleableUnitFacade;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaleableUnitServiceImpl implements SaleableUnitFacade {

    private SaleableUnitRepository repository;

    @Autowired
    public SaleableUnitServiceImpl(SaleableUnitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<SaleableUnit> register(SaleableUnit entity) {
        if (entity == null) {
            return Optional.empty();
        }
        return repository.save(entity);
    }

    @Override
    public Iterable<SaleableUnit> findAll(Pageable pager) {
        return repository.findAll(pager);
    }

    @Override
    public Optional<SaleableUnit> getOne(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return repository.findOne(id);
    }
}
