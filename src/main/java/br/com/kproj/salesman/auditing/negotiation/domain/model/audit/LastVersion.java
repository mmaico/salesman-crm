package br.com.kproj.salesman.auditing.negotiation.domain.model.audit;


import br.com.kproj.salesman.auditing.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class LastVersion implements ValueObject {

    private final Negotiation negotiation;


    public LastVersion(Negotiation negotiation) {
        this.negotiation = negotiation;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public static LastVersion of(Negotiation negotiation) {
        return new LastVersion(negotiation);
    }
}
