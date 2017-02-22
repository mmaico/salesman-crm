package br.com.kproj.salesman.sales.view;

import br.com.kproj.salesman.infrastructure.events.NegotiationClosedWonMessage;
import br.com.kproj.salesman.infrastructure.service.SerializerObject;
import br.com.kproj.salesman.sales.application.SalesOrderFacade;
import br.com.kproj.salesman.sales.application.dto.NegotiationDTO;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NegotiationSubscriber {


    private SerializerObject serializerObject;
    private SalesOrderFacade service;

    @Autowired
    public NegotiationSubscriber(SerializerObject serializerObject, SalesOrderFacade service) {
        this.serializerObject = serializerObject;
        this.service = service;
    }

    @Subscribe
    public void generateSalesOrder(NegotiationClosedWonMessage message) {
        NegotiationDTO negotiationDTO = serializerObject.deserialize(message.getMessage(), NegotiationDTO.class);
        service.generate(negotiationDTO);
    }
}
