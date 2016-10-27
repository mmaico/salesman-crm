package br.com.kproj.salesman.products_catalog.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.ServiceEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.services.Service;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.services.ServiceRepository;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.ServiceRepositorySpringData;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.ServiceEntityToServiceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceRepositoryHibernate extends BaseRespositoryImpl<Service, ServiceEntity> implements ServiceRepository {

    @Autowired
    private ServiceRepositorySpringData repository;

    @Autowired
    private ServiceEntityToServiceConverter converter;


    @Override
    public BaseRepositoryLegacy<ServiceEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ServiceEntity, Service> getConverter() {
        return converter;
    }

}
