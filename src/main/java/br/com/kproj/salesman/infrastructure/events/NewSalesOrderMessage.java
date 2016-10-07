package br.com.kproj.salesman.infrastructure.events;


import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.service.Serializer;

public class NewSalesOrderMessage {

    private final Long salesOrderId;
    private final String salesOrder;

    public NewSalesOrderMessage(Long salesOrderId, Object salesOrder) {
        Serializer transportFormat = ServiceLocator.getBean(Serializer.class);
        this.salesOrderId = salesOrderId;
        this.salesOrder = transportFormat.serialize(salesOrder);
    }

    public static NewSalesOrderMessage create(Long salesOrderId, Object object) {
        return new NewSalesOrderMessage(salesOrderId, object);
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public String getSalesOrder() {
        return salesOrder;
    }
}

