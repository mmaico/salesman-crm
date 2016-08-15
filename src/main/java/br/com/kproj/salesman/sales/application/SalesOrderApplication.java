package br.com.kproj.salesman.sales.application;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;


public interface SalesOrderApplication extends ModelLegacyService<SalesOrder> {

    SalesOrder register(BusinessProposal businessProposal);

    List<SalesOrder> findAllOrdered();


}
