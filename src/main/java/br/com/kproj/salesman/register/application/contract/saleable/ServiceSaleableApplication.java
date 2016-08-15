package br.com.kproj.salesman.register.application.contract.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.ServiceEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface ServiceSaleableApplication extends ModelLegacyService<ServiceEntity> {

    ServiceEntity register(ServiceEntity service);
}
