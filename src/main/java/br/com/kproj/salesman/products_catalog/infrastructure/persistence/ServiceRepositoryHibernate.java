package br.com.kproj.salesman.products_catalog.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.ServiceEntity;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.Service;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.ServiceRepository;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.ServiceRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ServiceRepositoryHibernate implements ServiceRepository {

    @Autowired
    private ServiceRepositorySpringData repository;


    @Override
    public Page<Service> findAll(Pageable page) {
        Page<ServiceEntity> servicesEntities = repository.findAll(page);
        return null;
    }

    @Override
    public Optional<Service> findOne(Long id) {
        ServiceEntity serviceEntity = repository.findOne(id);
        return null;
    }

    @Override
    public Optional<Service> save(Service entity) {
        return null;
    }
}
