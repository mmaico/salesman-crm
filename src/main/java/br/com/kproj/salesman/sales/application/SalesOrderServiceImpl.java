package br.com.kproj.salesman.sales.application;

import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.sales.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.sales.domain.model.sales.SalesOrderEventHandler;
import br.com.kproj.salesman.sales.domain.model.sales.SalesOrderRepository;
import br.com.kproj.salesman.sales.domain.model.system.SystemEvent;
import br.com.kproj.salesman.sales.view.dtos.negotiation.NegotiationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.sales.domain.model.system.Event.event;

@Service
public class SalesOrderServiceImpl extends BaseModelServiceImpl<SalesOrder> implements SalesOrderFacade {

    private SalesOrderRepository repository;
    private SalesOrderEventHandler eventHandler;


    @Autowired
    public SalesOrderServiceImpl(SalesOrderRepository repository, SalesOrderEventHandler eventHandler) {
        this.repository = repository;
        this.eventHandler = eventHandler;
    }

    @Override
    public BaseRepository<SalesOrder, Long> getRepository() {
        return repository;
    }

    @Override
    public void generate(NegotiationDTO negotiation) {

        SalesOrder newSalesOrder = SystemEvent.start().generationOfASaleBy(event(negotiation)).successfullyClosed();

        eventHandler.newSalesOrder(newSalesOrder);
    }
}
