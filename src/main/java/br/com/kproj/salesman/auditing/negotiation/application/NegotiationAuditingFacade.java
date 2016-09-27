package br.com.kproj.salesman.auditing.negotiation.application;


import br.com.kproj.salesman.auditing.negotiation.domain.model.audit.NewAuditingRequest;

public interface NegotiationAuditingFacade {

    void register(NewAuditingRequest request);
}
