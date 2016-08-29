package br.com.kproj.salesman.negotiation.infrastructure.events;


import br.com.kproj.salesman.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationEventHandler;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.infrastructure.events.NegotiationClosedWonMessage.createMessage;

@Service
public class NegotiationEventHandlerImpl implements NegotiationEventHandler {

    @Autowired
    private EventBus eventBus;

    @Override
    public void negotiationClosedWon(Negotiation message) {
        eventBus.post(createMessage(message));
    }
}
