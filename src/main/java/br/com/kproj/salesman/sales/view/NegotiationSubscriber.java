package br.com.kproj.salesman.sales.view;

import br.com.kproj.salesman.infrastructure.configuration.encapsulating.JsonSerializer;
import br.com.kproj.salesman.infrastructure.events.NegotiationClosedWonMessage;
import br.com.kproj.salesman.sales.view.dtos.negotiation.NegotiationDTO;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NegotiationSubscriber {

    @Autowired
    private JsonSerializer<NegotiationDTO> serializer;

    @Subscribe
    public void generateSalesOrder(NegotiationClosedWonMessage message) {
        NegotiationDTO negotiationDTO = serializer.deserialize(message.getMessage(), NegotiationDTO.class);


    }
}
