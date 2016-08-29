package br.com.kproj.salesman.negotiation2.domain.model.negotiation;


public interface NegotiationEventHandler {

    void negotiationClosedWon(Negotiation message);
}
