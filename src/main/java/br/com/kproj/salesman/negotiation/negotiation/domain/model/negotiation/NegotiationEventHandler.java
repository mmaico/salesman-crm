package br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation;


public interface NegotiationEventHandler {

    void possiblyChanged(Negotiation negotiation);
}
