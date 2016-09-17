package br.com.kproj.salesman.infrastructure.events;


public class NewSalesOrderMessage {

    private final Long salesOrderId;

    public NewSalesOrderMessage(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public static NewSalesOrderMessage create(Long salesOrderId) {
        return new NewSalesOrderMessage(salesOrderId);
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }
}

