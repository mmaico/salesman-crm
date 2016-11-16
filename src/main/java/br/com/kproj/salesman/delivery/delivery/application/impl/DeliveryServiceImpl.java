package br.com.kproj.salesman.delivery.delivery.application.impl;


import br.com.kproj.salesman.delivery.delivery.application.DeliveryFacade;
import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.DeliveryRepository;
import br.com.kproj.salesman.delivery.delivery.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("deliveryFacadeDeliveryModule")
public class DeliveryServiceImpl extends BaseModelServiceImpl<Delivery> implements DeliveryFacade {

    @Autowired
    private DeliveryRepository repository;

    @Override
    public Optional<Delivery> createFor(SalesOrder salesOrder) {
        Delivery delivery = new Delivery(salesOrder);
        Optional<Delivery> deliverySaved = repository.save(delivery);
        return deliverySaved;
    }


    @Override
    public BaseRepository<Delivery, Long> getRepository() {
        return repository;
    }
}
