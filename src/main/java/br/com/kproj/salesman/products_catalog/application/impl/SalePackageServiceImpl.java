package br.com.kproj.salesman.products_catalog.application.impl;

import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.products_catalog.application.SalePackageFacade;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SalePackage;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SalePackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalePackageServiceImpl extends BaseModelServiceImpl<SalePackage> implements SalePackageFacade {

    @Autowired
    private SalePackageRepository repository;

    @Override
    public BaseRepository<SalePackage, Long> getRepository() {
        return repository;
    }
}
