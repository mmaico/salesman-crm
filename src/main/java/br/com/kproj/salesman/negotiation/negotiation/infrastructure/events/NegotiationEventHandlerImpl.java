package br.com.kproj.salesman.negotiation.negotiation.infrastructure.events;


import br.com.kproj.salesman.infrastructure.events.NegotiationPossiblyChanged;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.NegotiationEventHandler;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NegotiationEventHandlerImpl implements NegotiationEventHandler {

    private EventBus eventBus;

    @Autowired
    public NegotiationEventHandlerImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void possiblyChanged(Negotiation negotiation) {
        eventBus.post(NegotiationPossiblyChanged.possiblyChanged(negotiation));
    }
}
