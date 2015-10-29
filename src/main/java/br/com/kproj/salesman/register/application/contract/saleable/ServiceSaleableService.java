package br.com.kproj.salesman.register.application.contract.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.Service;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface ServiceSaleableService extends ModelService<Service> {

    Service register(Service service);
}
