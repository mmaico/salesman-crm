package br.com.kproj.salesman.sales.application;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;


public interface SalesOrderApplication extends ModelLegacyService<SalesOrderEntity> {

    SalesOrderEntity register(BusinessProposalEntity businessProposalEntity);

    List<SalesOrderEntity> findAllOrdered();


}
