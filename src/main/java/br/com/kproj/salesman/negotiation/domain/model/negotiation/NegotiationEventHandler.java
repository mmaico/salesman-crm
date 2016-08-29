package br.com.kproj.salesman.negotiation.domain.model.negotiation;


public interface NegotiationEventHandler {

    void negotiationClosedWon(Negotiation message);
}
