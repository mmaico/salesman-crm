package br.com.kproj.salesman.infrastructure.events;


import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.service.Serializer;

public class EvaluationWasCompletedMessage {


    private final String message;

    public EvaluationWasCompletedMessage(String message) {
        this.message = message;
    }

    public EvaluationWasCompletedMessage(Object message) {
        Serializer transportFormat = ServiceLocator.getBean(Serializer.class);
        this.message = transportFormat.serialize(message);
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
