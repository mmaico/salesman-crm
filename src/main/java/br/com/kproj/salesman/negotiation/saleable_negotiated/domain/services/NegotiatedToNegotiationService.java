package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.services;

import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.Negotiation;

import java.util.Optional;

@FunctionalInterface
public interface NegotiatedToNegotiationService {

    Optional<Negotiated> to(Negotiation negotiation);
}
