package br.com.kproj.salesman.products_catalog.application.impl;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.products_catalog.application.SaleableUnitFacade;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleableUnitServiceImpl extends BaseModelServiceImpl<SaleableUnit> implements SaleableUnitFacade {

    @Autowired
    private SaleableUnitRepository repository;

    @Override
    public BaseRepository<SaleableUnit, Long> getRepository() {
        return repository;
    }
}
