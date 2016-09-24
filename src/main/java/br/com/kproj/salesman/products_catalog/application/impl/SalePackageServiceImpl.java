package br.com.kproj.salesman.products_catalog.application.impl;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.products_catalog.application.SalePackageFacade;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableValidator;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackageRepository;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static com.google.common.collect.Sets.newHashSet;

@Service
public class SalePackageServiceImpl extends BaseModelServiceImpl<SalePackage> implements SalePackageFacade {

    @Autowired
    private SalePackageRepository repository;

    @Autowired
    @Qualifier("saleableDomainValidator")
    private SaleableValidator validator;

    @Autowired
    @Qualifier("salePackageDomainValidator")
    private SalePackageValidator salePackageValidator;

    @Override
    public BaseRepository<SalePackage, Long> getRepository() {
        return repository;
    }

    @Override
    public Optional<SalePackage> register(SalePackage salePackage) {

        validator.checkRules(salePackage);

        return repository.save(salePackage);
    }

    @Override
    public Optional<SalePackage> addSaleable(SalePackage salePackage, SaleableUnit saleable) {

        Optional<SalePackage> packageFound = repository.findOne(salePackage.getId());

        if (!packageFound.isPresent()) {
            hasErrors(newHashSet("salepackage.not.found")).throwing(ValidationException.class);
        }

        salePackageValidator.checkRules(saleable);
        packageFound.get().addSaleable(saleable);

        return packageFound;
    }

    @Override
    public Optional<SalePackage> removeSaleable(SalePackage salePackage, SaleableUnit saleable) {
        Optional<SalePackage> packageFound = repository.findOne(salePackage.getId());

        if (!packageFound.isPresent()) {
            hasErrors(newHashSet("salepackage.not.found")).throwing(ValidationException.class);
        }

        packageFound.get().removeSaleable(saleable);

        return packageFound;
    }
}
