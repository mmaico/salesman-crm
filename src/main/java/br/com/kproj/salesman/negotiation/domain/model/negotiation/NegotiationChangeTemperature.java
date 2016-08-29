package br.com.kproj.salesman.negotiation.domain.model.negotiation;


import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class NegotiationChangeTemperature implements ValueObject {

    private final Negotiation negotiation;
    private final Temperature newTemperature;

    private NegotiationChangeTemperature(Negotiation negotiation, Temperature temperature) {
        this.negotiation = negotiation;
        this.newTemperature = temperature;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public Temperature getNewTemperature() {
        return newTemperature;
    }

    public static NegotiationChangeTemperature createChangeTemperature(Negotiation negotiation, Temperature newTemperature) {
        return new NegotiationChangeTemperature(negotiation, newTemperature);
    }

}
