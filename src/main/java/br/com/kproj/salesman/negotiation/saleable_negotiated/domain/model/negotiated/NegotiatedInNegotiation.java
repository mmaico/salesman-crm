package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated;


import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.Negotiation;

public class NegotiatedInNegotiation {

    private final Long negotiationId;
    private final Negotiated negotiated;


    public NegotiatedInNegotiation(Long negotiationId, Negotiated negotiated) {
        this.negotiationId = negotiationId;
        this.negotiated = negotiated;
    }


    public Negotiation getNegotiation() {
        return new Negotiation(negotiationId);
    }

    public Negotiated getNegotiated() {
        return negotiated;
    }

    public static NegotiatedInNegotiation create(Long negotiationId, Negotiated negotiated) {
        return new NegotiatedInNegotiation(negotiationId, negotiated);
    }
}
