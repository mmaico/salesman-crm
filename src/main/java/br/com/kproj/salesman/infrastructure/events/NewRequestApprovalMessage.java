package br.com.kproj.salesman.infrastructure.events;


import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.service.Serializer;


public class NewRequestApprovalMessage {


    private final String message;

    public NewRequestApprovalMessage(String message) {
        this.message = message;
    }

    public NewRequestApprovalMessage(Object message) {
        Serializer transportFormat = ServiceLocator.getBean(Serializer.class);
        this.message = transportFormat.serialize(message);
    }

    public static NewRequestApprovalMessage createMessage(String message) {
        return new NewRequestApprovalMessage(message);
    }

    public static NewRequestApprovalMessage createMessage(Object message) {
        return new NewRequestApprovalMessage(message);
    }

    public String getMessage() {
        return message;
    }
}
