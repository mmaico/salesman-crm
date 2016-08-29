package br.com.kproj.salesman.negotiation2.application;

import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.negotiation2.domain.model.account.Account;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.NegotiationChangeTemperature;
import br.com.kproj.salesman.negotiation2.domain.model.seller.Seller;

import java.util.Collection;
import java.util.Optional;

public interface NegotiationFacade extends ModelFacade<Negotiation> {

    Optional<Negotiation> register(Negotiation negotiation);

    Collection<Negotiation> findOne(Account account);

    void changeTemperature(Seller seller, NegotiationChangeTemperature changeTemperature);

}
