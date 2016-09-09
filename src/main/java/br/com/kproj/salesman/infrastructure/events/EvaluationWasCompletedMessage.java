package br.com.kproj.salesman.infrastructure.events;


import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.helpers.transportformat.TransportFormat;

public class EvaluationWasCompletedMessage {


    private final String message;

    public EvaluationWasCompletedMessage(String message) {
        this.message = message;
    }

    public EvaluationWasCompletedMessage(Object message) {
        TransportFormat transportFormat = ServiceLocator.getBean(TransportFormat.class);
        this.message = transportFormat.format(message);
    }

    public static NegotiationClosedWonMessage createMessage(String message) {
        return new NegotiationClosedWonMessage(message);
    }

    public static NegotiationClosedWonMessage createMessage(Object message) {
        return new NegotiationClosedWonMessage(message);
    }

    public String getMessage() {
        return message;
    }
}
