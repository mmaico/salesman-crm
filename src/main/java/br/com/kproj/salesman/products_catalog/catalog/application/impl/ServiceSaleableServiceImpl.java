package br.com.kproj.salesman.products_catalog.catalog.application.impl;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.products_catalog.catalog.application.ServiceSaleableFacade;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.services.Service;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.services.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceSaleableServiceImpl extends BaseModelServiceImpl<Service> implements ServiceSaleableFacade {

    @Autowired
    private ServiceRepository repository;

    @Override
    public BaseRepository<Service, Long> getRepository() {
        return repository;
    }
}
