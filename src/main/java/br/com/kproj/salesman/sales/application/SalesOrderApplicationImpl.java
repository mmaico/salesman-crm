package br.com.kproj.salesman.sales.application;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.events.messages.NewSalesOrderMessage;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.SalesOrderRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import br.com.kproj.salesman.sales.infrastructure.generatebyproposal.convert.ProposalToSaleOrder;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Service
public class SalesOrderApplicationImpl extends BaseModelServiceLegacyImpl<SalesOrder> implements SalesOrderApplication {


    @Autowired
    private SalesOrderRepository repository;

    @Autowired
    private ProposalToSaleOrder converter;

    @Autowired
    private EventBus eventBus;


    @Override
    public SalesOrder register(BusinessProposalEntity businessProposalEntity) {
        Optional<SalesOrder> result = repository.findByProposal(businessProposalEntity);
        if (result.isPresent()) {
            hasErrors(Sets.newHashSet("already.exists.sales.order.to.domain"))
                        .throwing(ValidationException.class);
        }

        SalesOrder salesOrder = converter.convert(businessProposalEntity);
        SalesOrder salesOrderSaved = super.save(salesOrder);

        eventBus.post(NewSalesOrderMessage.create(salesOrderSaved));

        return salesOrderSaved;
    }

    @Override
    public List<SalesOrder> findAllOrdered() {
        return repository.findAllOrdered();
    }


    public BaseRepositoryLegacy<SalesOrder, Long> getRepository() {
        return repository;
    }


}
