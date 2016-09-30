package br.com.kproj.salesman.negotiation.domain.service;

import br.com.kproj.salesman.negotiation.domain.model.negotiation.Negotiation;

@FunctionalInterface
public interface NegotiationChangeTemperatureService {


    void from(Negotiation negotiation);

}
