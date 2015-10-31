package br.com.kproj.salesman.register.application.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SalesPackageRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.saleable.SalePackageService;
import br.com.kproj.salesman.register.domain.contract.SaleableUnitDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalesPackageServiceImpl extends BaseModelServiceImpl<SalePackage> implements SalePackageService {

    private SaleableUnitDomainService domainService;

    private SalesPackageRepository salesPackageRepository;

    @Autowired
    public SalesPackageServiceImpl(SalesPackageRepository salesPackageRepository, SaleableUnitDomainService domainService) {
        this.salesPackageRepository = salesPackageRepository;
        this.domainService = domainService;
    }

    @Override
    public SalePackage register(SalePackage salePackageItem) {
        domainService.verifyPreconditionToSave(salePackageItem);
        return super.save(salePackageItem);
    }

    public Optional<SalePackage> getOne(Long id) {
        return salesPackageRepository.getOne(id);
    }

    @Override
    public BaseRepository<SalePackage, Long> getRepository() {
        return salesPackageRepository;
    }

}
