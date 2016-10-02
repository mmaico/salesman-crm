package br.com.kproj.salesman.sales.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.sales.domain.model.negotiation.INegotiation;
import br.com.kproj.salesman.sales.domain.model.sales.SalesOrder;

public interface SalesOrderFacade extends ModelFacade<SalesOrder> {

    void generate(INegotiation negotiation);
}
