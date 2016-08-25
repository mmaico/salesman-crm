package br.com.kproj.salesman.negotiation2.domain.model.negotiation;


import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class NegotiationToChangeTemperature implements ValueObject {

    private final Negotiation negotiation;
    private final Temperature newTemperature;

    private NegotiationToChangeTemperature (Negotiation negotiation, Temperature temperature) {
        this.negotiation = negotiation;
        this.newTemperature = temperature;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public Temperature getNewTemperature() {
        return newTemperature;
    }

    public static NegotiationToChangeTemperature create(Negotiation negotiation, Temperature newTemperature) {
        return new NegotiationToChangeTemperature(negotiation, newTemperature);
    }

}
