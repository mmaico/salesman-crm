package br.com.kproj.salesman.register.application.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.Service;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.Saleable.ServiceRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.saleable.ServiceSaleableApplication;
import br.com.kproj.salesman.register.domain.contract.SaleableUnitDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


@org.springframework.stereotype.Service
public class ServiceSaleableApplicationImpl extends BaseModelServiceImpl<Service> implements ServiceSaleableApplication {

    private SaleableUnitDomainService domainService;

    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceSaleableApplicationImpl(ServiceRepository serviceRepository, SaleableUnitDomainService domainService) {
        this.serviceRepository = serviceRepository;
        this.domainService = domainService;
    }

    @Override
    public Service register(Service saleableUnit) {
        return super.save(saleableUnit, domainService);
    }

    @Override
    public Page<Service> findAll(Pageable pageable) {
        return this.serviceRepository.findAll(pageable);
    }

    @Override
    public Optional<Service> getOne(Long id) {
        return serviceRepository.getOne(id);
    }

    public BaseRepository<Service, Long> getRepository() {
        return serviceRepository;
    }

}
