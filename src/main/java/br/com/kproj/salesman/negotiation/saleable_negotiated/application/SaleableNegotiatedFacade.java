package br.com.kproj.salesman.negotiation.saleable_negotiated.application;

import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.NegotiatedInNegotiation;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.Negotiation;

import java.util.Collection;
import java.util.Optional;

public interface SaleableNegotiatedFacade extends ModelFacade<Negotiated> {


    Optional<Negotiated> register(NegotiatedInNegotiation negotiatedInNegotiation);

    Negotiated update(Negotiated negotiation);

    Collection<Negotiated> findAll(Negotiation negotiation);

}
