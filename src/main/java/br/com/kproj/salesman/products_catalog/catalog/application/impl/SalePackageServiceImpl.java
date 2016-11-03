package br.com.kproj.salesman.products_catalog.catalog.application.impl;

import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.products_catalog.catalog.application.SalePackageFacade;
import br.com.kproj.salesman.products_catalog.catalog.application.validators.IgnoreRules;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackageRepository;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.products_catalog.catalog.application.validators.IgnoreRules.rulePackageNotExists;

@Service
public class SalePackageServiceImpl extends BaseModelServiceImpl<SalePackage> implements SalePackageFacade {

    @Autowired
    private SalePackageRepository repository;

    @Autowired
    private SalePackageValidator validator;

    public SalePackageServiceImpl(SalePackageRepository repository, SalePackageValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Optional<SalePackage> register(SalePackage salePackage) {
        validator.checkRules(salePackage, IgnoreRules.add(rulePackageNotExists()));
        return repository.save(salePackage);
    }

    @Override
    public void addSaleable(SalePackage salePackage, SaleableUnit saleable) {
        validator.checkRules(salePackage);
        salePackage.addSaleable(saleable);
    }

    @Override
    public void removeSaleable(SalePackage salePackage, SaleableUnit saleable) {
        validator.checkRules(salePackage);
        salePackage.removeSaleable(saleable);
    }

    @Override
    public BaseRepository<SalePackage, Long> getRepository() {
        return repository;
    }
}
