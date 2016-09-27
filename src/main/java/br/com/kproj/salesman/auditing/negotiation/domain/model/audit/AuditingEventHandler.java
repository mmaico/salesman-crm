package br.com.kproj.salesman.auditing.negotiation.domain.model.audit;


import java.util.Optional;

public interface AuditingEventHandler {

    void negotiationAudited(Optional<Auditing> current, Optional<Auditing> last);
}
