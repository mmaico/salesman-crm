package br.com.kproj.salesman.negotiation.application;

import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.negotiation.domain.model.account.Account;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationChangeTemperature;
import br.com.kproj.salesman.negotiation.domain.model.seller.Seller;
import br.com.kproj.salesman.negotiation.domain.model.seller.SellerSaveNegotiation;

import java.util.Collection;
import java.util.Optional;

public interface NegotiationFacade extends ModelFacade<Negotiation> {

    Optional<Negotiation> register(SellerSaveNegotiation saveNegotiation);

    Collection<Negotiation> findOne(Account account);

    void changeTemperature(Seller seller, NegotiationChangeTemperature changeTemperature);

}
