package br.com.kproj.salesman.products_catalog.application.impl;


import br.com.kproj.salesman.products_catalog.application.SaleableUnitFacade;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnitRepository;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaleableUnitServiceImpl implements SaleableUnitFacade {

    private SaleableUnitRepository repository;

    private SaleableValidator validator;

    @Autowired
    public SaleableUnitServiceImpl(SaleableUnitRepository repository, SaleableValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Optional<SaleableUnit> register(SaleableUnit saleable) {
        validator.checkRules(saleable);
        return repository.save(saleable);
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
