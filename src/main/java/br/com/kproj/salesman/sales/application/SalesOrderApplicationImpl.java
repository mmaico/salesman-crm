package br.com.kproj.salesman.sales.application;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
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
public class SalesOrderApplicationImpl extends BaseModelServiceLegacyImpl<SalesOrderEntity> implements SalesOrderApplication {


    @Autowired
    private SalesOrderRepository repository;

    @Autowired
    private ProposalToSaleOrder converter;

    @Autowired
    private EventBus eventBus;


    @Override
    public SalesOrderEntity register(BusinessProposalEntity businessProposalEntity) {
        Optional<SalesOrderEntity> result = repository.findByProposal(businessProposalEntity);
        if (result.isPresent()) {
            hasErrors(Sets.newHashSet("already.exists.sales.order.to.domain"))
                        .throwing(ValidationException.class);
        }

        SalesOrderEntity salesOrderEntity = converter.convert(businessProposalEntity);
        SalesOrderEntity salesOrderEntitySaved = super.save(salesOrderEntity);

        eventBus.post(NewSalesOrderMessage.create(salesOrderEntitySaved));

        return salesOrderEntitySaved;
    }

    @Override
    public List<SalesOrderEntity> findAllOrdered() {
        return repository.findAllOrdered();
    }


    public BaseRepositoryLegacy<SalesOrderEntity, Long> getRepository() {
        return repository;
    }


}
