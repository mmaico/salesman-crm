package br.com.kproj.salesman.register.application.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SalesPackageRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.saleable.SalePackageApplication;
import br.com.kproj.salesman.register.domain.contract.SaleableUnitDomainService;
import br.com.kproj.salesman.register.domain.contract.SalesPackageAddSaleableDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static com.google.common.collect.Sets.newHashSet;

@Service("salePackageApplication")
public class SalesPackageApplicationImpl extends BaseModelServiceImpl<SalePackage> implements SalePackageApplication {

    @Autowired
    private SaleableUnitDomainService domainService;

    @Autowired
    private SalesPackageRepository salesPackageRepository;

    @Autowired
    private SalesPackageAddSaleableDomainService addSaleableDomainService;


    @Override
    public SalePackage register(SalePackage salePackageItem) {

        return super.save(salePackageItem, domainService);
    }

    @Override
    public Page<SalePackage> findAll(Pageable pageable) {
        return this.salesPackageRepository.findAll(pageable);
    }

    @Override
    public SalePackage addProductOrService(SalePackage salePackage, SaleableUnit saleable) {

        Optional<SalePackage> salePackageLoaded = salesPackageRepository.getOne(salePackage.getId());

        if (!salePackageLoaded.isPresent()) {
            hasErrors(newHashSet("salepackage.not.found")).throwing(ValidationException.class);
        }
        addSaleableDomainService.checkBusinessRulesFor(saleable);
        salePackageLoaded.get().addSaleableUnit(saleable);

        return salePackageLoaded.get();
    }

    @Override
    public SalePackage removeProductOrService(SalePackage salePackage, SaleableUnit saleable) {

        Optional<SalePackage> salePackageLoaded = salesPackageRepository.getOne(salePackage.getId());

        if (!salePackageLoaded.isPresent()) {
            hasErrors(newHashSet("salepackage.not.found")).throwing(ValidationException.class);
        }

        salePackageLoaded.get().removeSaleableUnit(saleable);

        return salePackageLoaded.get();
    }

    public Optional<SalePackage> getOne(Long id) {
        return salesPackageRepository.getOne(id);
    }

    public BaseRepository<SalePackage, Long> getRepository() {
        return salesPackageRepository;
    }

}
