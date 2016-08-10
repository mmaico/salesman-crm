package br.com.kproj.salesman.register.application.contract.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.ServiceEntity;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface ServiceSaleableApplication extends ModelService<ServiceEntity> {

    ServiceEntity register(ServiceEntity service);
}
