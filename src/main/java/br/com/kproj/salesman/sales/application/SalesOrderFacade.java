package br.com.kproj.salesman.sales.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.sales.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.sales.view.dtos.negotiation.NegotiationDTO;

public interface SalesOrderFacade extends ModelFacade<SalesOrder> {

    void generate(NegotiationDTO negotiationDTO);
}
