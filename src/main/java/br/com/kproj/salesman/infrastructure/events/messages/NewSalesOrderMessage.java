package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;

public class NewSalesOrderMessage {

    private SalesOrderEntity salesOrderEntity;

    public NewSalesOrderMessage(SalesOrderEntity salesOrderEntity) {
        this.salesOrderEntity = salesOrderEntity;
    }

    public static NewSalesOrderMessage create(SalesOrderEntity salesOrderEntity) {
        return new NewSalesOrderMessage(salesOrderEntity);
    }

    public SalesOrderEntity getSalesOrderEntity() {
        return salesOrderEntity;
    }

}

