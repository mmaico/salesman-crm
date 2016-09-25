package br.com.kproj.salesman.sales.domain.model.sales;


public interface SalesOrderEventHandler {

    void newSalesOrder(SalesOrder message);
}
