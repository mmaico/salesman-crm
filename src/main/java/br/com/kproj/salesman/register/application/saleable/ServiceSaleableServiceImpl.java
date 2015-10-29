package br.com.kproj.salesman.register.application.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.Product;
import br.com.kproj.salesman.infrastructure.entity.saleable.Service;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.Saleable.ServiceRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.saleable.ServiceSaleableService;
import br.com.kproj.salesman.register.domain.contract.SaleableUnitDomainService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


@org.springframework.stereotype.Service
public class ServiceSaleableServiceImpl extends BaseModelServiceImpl<Service> implements ServiceSaleableService {

    private SaleableUnitDomainService domainService;

    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceSaleableServiceImpl(ServiceRepository serviceRepository, SaleableUnitDomainService domainService) {
        this.serviceRepository = serviceRepository;
        this.domainService = domainService;
    }

    @Override
    public Service register(Service saleableUnit) {
        domainService.verifyPreconditionToSave(saleableUnit);
        return super.save(saleableUnit);
    }


    @Override
    public Optional<Service> getOne(Long id) {
        return serviceRepository.getOne(id);
    }

    @Override
    public BaseRepository<Service, Long> getRepository() {
        return serviceRepository;
    }

}
