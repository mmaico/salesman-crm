package br.com.kproj.salesman.auditing.delivery.application;


import br.com.kproj.salesman.auditing.delivery.domain.model.audit.NewAuditingRequest;

public interface TaskAuditingFacade {

    void register(NewAuditingRequest request);
}
