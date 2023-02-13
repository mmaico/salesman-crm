package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.ServiceEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.Represent;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.services.Service;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.services.ServiceRepository;
import br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata.SaleableUnitRepositorySpringData;
import br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata.ServiceRepositorySpringData;
import com.github.mmaico.clone.BusinessModelClone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.github.mmaico.clone.BusinessModelClone.from;

@Repository
public class ServiceRepositoryHibernate extends BaseRespositoryImpl<Service, ServiceEntity> implements ServiceRepository {

    private ServiceRepositorySpringData repository;

    private SaleableUnitRepositorySpringData saleableRepository;

    @Autowired
    public ServiceRepositoryHibernate(ServiceRepositorySpringData repository, SaleableUnitRepositorySpringData saleableRepository) {
        this.repository = repository;
        this.saleableRepository = saleableRepository;
    }

    public Optional<Service> save(Service service) {

        Optional<ServiceEntity> serviceEntity = repository.getOne(service.getId());
        if (serviceEntity.isPresent()) {
            from(service).merge(serviceEntity.get());
            return Optional.of(getConverter().convert(serviceEntity.get()));
        } else {
            ServiceEntity newService = from(service).convertTo(ServiceEntity.class);
            newService.setSaleable(new SaleableUnitEntity(service.getId()));
            ServiceEntity entity = repository.save(newService);
            saleableRepository.represent(entity.getId(), Represent.SERVICE);

            return Optional.ofNullable(getConverter().convert(entity));
        }
    }

    @Override
    public BaseRepositoryLegacy<ServiceEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ServiceEntity, Service> getConverter() {
        return ((serviceEntity, args) -> {
            Service service = new Service();
            BusinessModelClone.from(serviceEntity.getSaleable()).merge(service);
            return service;
        });
    }

}
