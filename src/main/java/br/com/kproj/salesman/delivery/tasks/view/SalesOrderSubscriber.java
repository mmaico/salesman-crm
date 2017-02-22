package br.com.kproj.salesman.delivery.tasks.view;


import br.com.kproj.salesman.delivery.tasks.application.TaskFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.infrastructure.events.NewSalesOrderMessage;
import br.com.kproj.salesman.infrastructure.service.SerializerObject;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesOrderSubscriber {

    private SerializerObject serializerObject;
    private TaskFacade service;

    @Autowired
    public SalesOrderSubscriber(SerializerObject serializerObject, TaskFacade service) {
        this.serializerObject = serializerObject;
        this.service = service;
    }

    @Subscribe
    public void receiveNewSalesOrder(NewSalesOrderMessage message) {
        SalesOrder salesOrder = serializerObject.deserialize(message.getSalesOrder(), SalesOrder.class);
        service.generateByNewSalesOrder(salesOrder);
    }
}
