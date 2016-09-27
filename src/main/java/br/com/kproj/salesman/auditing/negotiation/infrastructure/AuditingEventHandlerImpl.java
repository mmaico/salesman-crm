package br.com.kproj.salesman.auditing.negotiation.infrastructure;


import br.com.kproj.salesman.auditing.negotiation.domain.model.audit.Auditing;
import br.com.kproj.salesman.auditing.negotiation.domain.model.audit.AuditingEventHandler;
import br.com.kproj.salesman.auditing.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.auditing.negotiation.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.events.NegotiationChangedMessage;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.events.NegotiationChangedMessage.newChanged;

@Component
public class AuditingEventHandlerImpl implements AuditingEventHandler {

    private EventBus eventBus;

    @Autowired
    public AuditingEventHandlerImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void negotiationAudited(Optional<Auditing> current, Optional<Auditing> last) {
        User user = current.get().getUser();
        String currentVersionData = current.get().getData();
        Negotiation negotiation = current.get().getNegotiation();

        NegotiationChangedMessage message = newChanged(negotiation.getId(), user.getId(), last.get().getData(), currentVersionData);
        eventBus.post(message);
    }
}
