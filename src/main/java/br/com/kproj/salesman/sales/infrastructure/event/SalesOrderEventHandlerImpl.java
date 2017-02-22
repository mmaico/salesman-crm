package br.com.kproj.salesman.sales.infrastructure.event;


import br.com.kproj.salesman.infrastructure.events.NewSalesOrderMessage;
import br.com.kproj.salesman.infrastructure.service.SerializerObject;
import br.com.kproj.salesman.sales.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.sales.domain.model.sales.SalesOrderEventHandler;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesOrderEventHandlerImpl implements SalesOrderEventHandler {

    private EventBus eventBus;

    private SerializerObject serializerObject;

    @Autowired
    public SalesOrderEventHandlerImpl(EventBus eventBus, SerializerObject serializerObject) {
        this.eventBus = eventBus;
        this.serializerObject = serializerObject;
    }

    @Override
    public void newSalesOrder(SalesOrder salesOrder) {
        eventBus.post(NewSalesOrderMessage.create(salesOrder.getId(), salesOrder));
    }
}
