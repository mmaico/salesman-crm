package br.com.kproj.salesman.infrastructure.events;


import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.service.Serializer;

public class NegotiationPossiblyChanged {

    private final String message;


    public NegotiationPossiblyChanged(String message) {
        this.message = message;
    }

    public NegotiationPossiblyChanged(Object message) {
        Serializer transportFormat = ServiceLocator.getBean(Serializer.class);
        this.message = transportFormat.serialize(message);
    }

    public String getMessage() {
        return message;
    }

    public static NegotiationPossiblyChanged createNegotiationMessage(Object object) {
        return new NegotiationPossiblyChanged(object);
    }
}
