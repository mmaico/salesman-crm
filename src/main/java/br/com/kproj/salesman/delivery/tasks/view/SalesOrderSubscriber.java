package br.com.kproj.salesman.delivery.tasks.view;


import br.com.kproj.salesman.delivery.tasks.application.TaskFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.infrastructure.events.NewSalesOrderMessage;
import br.com.kproj.salesman.infrastructure.service.Serializer;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesOrderSubscriber {

    private Serializer serializer;
    private TaskFacade service;

    @Autowired
    public SalesOrderSubscriber(Serializer serializer, TaskFacade service) {
        this.serializer = serializer;
        this.service = service;
    }

    @Subscribe
    public void receiveNewSalesOrder(NewSalesOrderMessage message) {
        SalesOrder salesOrder = serializer.deserialize(message.getSalesOrder(), SalesOrder.class);
        service.generateByNewSalesOrder(salesOrder);
    }
}
