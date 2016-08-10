package br.com.kproj.salesman.register.application.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
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
public class SalesPackageApplicationImpl extends BaseModelServiceImpl<SalePackageEntity> implements SalePackageApplication {

    @Autowired
    private SaleableUnitDomainService domainService;

    @Autowired
    private SalesPackageRepository salesPackageRepository;

    @Autowired
    private SalesPackageAddSaleableDomainService addSaleableDomainService;


    @Override
    public SalePackageEntity register(SalePackageEntity salePackageItem) {

        return super.save(salePackageItem, domainService);
    }

    @Override
    public Page<SalePackageEntity> findAll(Pageable pageable) {
        return this.salesPackageRepository.findAll(pageable);
    }

    @Override
    public SalePackageEntity addProductOrService(SalePackageEntity salePackage, SaleableUnitEntity saleable) {

        Optional<SalePackageEntity> salePackageLoaded = salesPackageRepository.getOne(salePackage.getId());

        if (!salePackageLoaded.isPresent()) {
            hasErrors(newHashSet("salepackage.not.found")).throwing(ValidationException.class);
        }
        addSaleableDomainService.checkBusinessRulesFor(saleable);
        salePackageLoaded.get().addSaleableUnit(saleable);

        return salePackageLoaded.get();
    }

    @Override
    public SalePackageEntity removeProductOrService(SalePackageEntity salePackage, SaleableUnitEntity saleable) {

        Optional<SalePackageEntity> salePackageLoaded = salesPackageRepository.getOne(salePackage.getId());

        if (!salePackageLoaded.isPresent()) {
            hasErrors(newHashSet("salepackage.not.found")).throwing(ValidationException.class);
        }

        salePackageLoaded.get().removeSaleableUnit(saleable);

        return salePackageLoaded.get();
    }

    public Optional<SalePackageEntity> getOne(Long id) {
        return salesPackageRepository.getOne(id);
    }

    public BaseRepository<SalePackageEntity, Long> getRepository() {
        return salesPackageRepository;
    }

}
