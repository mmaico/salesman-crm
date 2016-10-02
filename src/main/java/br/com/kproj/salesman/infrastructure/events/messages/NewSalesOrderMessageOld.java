package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;

@Deprecated
public class NewSalesOrderMessageOld {

    private SalesOrderEntity salesOrderEntity;

    public NewSalesOrderMessageOld(SalesOrderEntity salesOrderEntity) {
        this.salesOrderEntity = salesOrderEntity;
    }

    public static NewSalesOrderMessageOld create(SalesOrderEntity salesOrderEntity) {
        return new NewSalesOrderMessageOld(salesOrderEntity);
    }

    public SalesOrderEntity getSalesOrderEntity() {
        return salesOrderEntity;
    }

}

