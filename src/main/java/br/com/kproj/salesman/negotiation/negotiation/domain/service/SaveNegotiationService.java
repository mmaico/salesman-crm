package br.com.kproj.salesman.negotiation.negotiation.domain.service;

import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Temperature;

import java.util.Optional;

@FunctionalInterface
public interface SaveNegotiationService {

    Optional<Negotiation> withInitial(Temperature temperature);
}
