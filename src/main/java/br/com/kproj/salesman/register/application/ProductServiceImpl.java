package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.SaleableUnitRepository;
import br.com.kproj.salesman.infrastructure.service.impl.BaseModelServiceImpl;
import br.com.kproj.salesman.register.domain.SaleableUnitDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseModelServiceImpl<SaleableUnit> implements SaleableUnitService {

    private SaleableUnitDomainService domainService;

    private SaleableUnitRepository productRepository;

    @Autowired
    public ProductServiceImpl(SaleableUnitRepository productRepository, SaleableUnitDomainService domainService) {
        this.productRepository = productRepository;
        this.domainService = domainService;
    }

    @Override
    public SaleableUnit register(SaleableUnit saleableUnit) {
        domainService.verifyPreconditionToSave(saleableUnit);
        return super.save(saleableUnit);
    }

    @Override
    public BaseRepository<SaleableUnit, Long> getRepository() {
        return productRepository;
    }

}
