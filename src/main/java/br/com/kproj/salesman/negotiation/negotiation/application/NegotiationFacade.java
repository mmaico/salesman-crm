package br.com.kproj.salesman.negotiation.negotiation.application;

import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.customer.Customer;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.SellerSaveNegotiation;

import java.util.Collection;
import java.util.Optional;

public interface NegotiationFacade extends ModelFacade<Negotiation> {

    Optional<Negotiation> register(SellerSaveNegotiation saveNegotiation);

    Optional<Negotiation> update(Negotiation negotiation);

    Collection<Negotiation> findOne(Customer account);


}
