package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated;


import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;

public class NegotiatedInNegotiation {

    private final Long negotiationId;
    private final Negotiated negotiated;
    private final Saleable saleable;


    public NegotiatedInNegotiation(Long negotiationId, Negotiated negotiated, Saleable saleable) {
        this.negotiationId = negotiationId;
        this.negotiated = negotiated;
        this.saleable = saleable;
    }


    public Negotiation getNegotiation() {
        return new Negotiation(negotiationId);
    }

    public Negotiated getNegotiated() {
        return negotiated;
    }

    public Saleable getSaleable() {
        return saleable;
    }

    public static NegotiatedInNegotiation negotiatedInNegotiation(Long negotiationId, Negotiated negotiated, Saleable saleable) {
        return new NegotiatedInNegotiation(negotiationId, negotiated, saleable);
    }
}
