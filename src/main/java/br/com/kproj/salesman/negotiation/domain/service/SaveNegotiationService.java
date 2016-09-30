package br.com.kproj.salesman.negotiation.domain.service;

import br.com.kproj.salesman.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.Temperature;

import java.util.Optional;

@FunctionalInterface
public interface SaveNegotiationService {

    Optional<Negotiation> whenNewUse(Temperature temperature);
}
