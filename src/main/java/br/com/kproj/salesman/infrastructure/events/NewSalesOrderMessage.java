package br.com.kproj.salesman.infrastructure.events;


import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.helpers.transportformat.TransportFormat;

public class NewSalesOrderMessage {

    private final Long salesOrderId;
    private final String salesOrder;

    public NewSalesOrderMessage(Long salesOrderId, Object salesOrder) {
        TransportFormat transportFormat = ServiceLocator.getBean(TransportFormat.class);
        this.salesOrderId = salesOrderId;
        this.salesOrder = transportFormat.format(salesOrder);
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

