package br.com.kproj.salesman.auditing.negotiation.domain.service;



import br.com.kproj.salesman.auditing.negotiation.domain.model.audit.Auditing;

import java.util.Optional;

@FunctionalInterface
public interface NegotiationNewVersion {

    Optional<Auditing> andTheNewVersionIs(String data);
}
