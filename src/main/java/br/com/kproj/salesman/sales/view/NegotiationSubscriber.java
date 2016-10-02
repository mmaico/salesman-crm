package br.com.kproj.salesman.sales.view;

import br.com.kproj.salesman.infrastructure.events.NegotiationClosedWonMessage;
import br.com.kproj.salesman.infrastructure.service.Serializer;
import br.com.kproj.salesman.sales.application.SalesOrderFacade;
import br.com.kproj.salesman.sales.application.dto.NegotiationDTO;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NegotiationSubscriber {


    private Serializer serializer;
    private SalesOrderFacade service;

    @Autowired
    public NegotiationSubscriber(Serializer serializer, SalesOrderFacade service) {
        this.serializer = serializer;
        this.service = service;
    }

    @Subscribe
    public void generateSalesOrder(NegotiationClosedWonMessage message) {
        NegotiationDTO negotiationDTO = serializer.deserialize(message.getMessage(), NegotiationDTO.class);
        service.generate(negotiationDTO);
    }
}
