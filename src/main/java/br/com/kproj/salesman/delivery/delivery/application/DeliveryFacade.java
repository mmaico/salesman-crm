package br.com.kproj.salesman.delivery.delivery.application;


import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.delivery.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface DeliveryFacade extends ModelFacade<Delivery> {

    Optional<Delivery> createFor(SalesOrder salesOrder);


}
