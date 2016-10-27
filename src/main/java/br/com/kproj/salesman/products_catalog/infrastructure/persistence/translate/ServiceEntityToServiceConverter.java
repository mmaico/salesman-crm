package br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.saleable.ServiceEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ServiceEntityToServiceConverter implements Converter<ServiceEntity, Service> {

    @Autowired
    private SaleableUnitEntityConverter saleableUnitConverter;


    @Override
    public Service convert(ServiceEntity serviceEntity, Object... args) {
        Service service = new Service();

        saleableUnitConverter.convert(serviceEntity, service);

        return service;
    }
}
