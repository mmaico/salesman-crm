package br.com.kproj.salesman.negotiation2.domain.model.negotiation;


import br.com.kproj.salesman.negotiation2.domain.model.seller.Seller;

public interface RulesToChangeTemperature {

    Boolean isValidBusinessRulesFor(Seller seller, NegotiationChangeTemperature negotiation);
}
