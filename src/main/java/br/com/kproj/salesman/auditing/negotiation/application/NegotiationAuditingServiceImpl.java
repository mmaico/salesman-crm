package br.com.kproj.salesman.auditing.negotiation.application;


import br.com.kproj.salesman.auditing.negotiation.domain.model.audit.*;
import br.com.kproj.salesman.auditing.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.auditing.negotiation.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NegotiationAuditingServiceImpl implements NegotiationAuditingFacade {

    private AuditingRepository repository;

    private AuditingEventHandler eventHandler;

    @Autowired
    public NegotiationAuditingServiceImpl(AuditingRepository repository, AuditingEventHandler eventHandler) {
        this.repository = repository;
        this.eventHandler = eventHandler;
    }

    @Override
    public void register(NewAuditingRequest request) {
        User user = request.getUser();
        Negotiation negotiation = request.getNegotiation();
        String currentVersion = negotiation.getCurrentVersion();

        Optional<Auditing> lastVersionAudit = repository.findOne(LastVersion.of(negotiation));

        Boolean wasChanged = negotiation.wasChanged().whenComparedWith(lastVersionAudit);

        if (wasChanged) {
            Optional<Auditing> currentAudit = user.changed(negotiation).andTheNewVersionIs(currentVersion);
            eventHandler.negotiationAudited(currentAudit, lastVersionAudit);
        }
    }
}
