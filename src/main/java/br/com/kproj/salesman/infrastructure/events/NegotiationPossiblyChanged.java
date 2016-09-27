package br.com.kproj.salesman.infrastructure.events;


import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.helpers.transportformat.TransportFormat;

public class NegotiationPossiblyChanged {

    private final String message;


    public NegotiationPossiblyChanged(String message) {
        this.message = message;
    }

    public NegotiationPossiblyChanged(Object message) {
        TransportFormat transportFormat = ServiceLocator.getBean(TransportFormat.class);
        this.message = transportFormat.format(message);
    }

    public String getMessage() {
        return message;
    }

    public static NegotiationPossiblyChanged createNegotiationMessage(Object object) {
        return new NegotiationPossiblyChanged(object);
    }
}
