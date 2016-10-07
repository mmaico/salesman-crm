package br.com.kproj.salesman.infrastructure.events;


import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.service.Serializer;

public class TaskPossiblyChanged {

    private final String message;


    public TaskPossiblyChanged(String message) {
        this.message = message;
    }

    public TaskPossiblyChanged(Object message) {
        Serializer transportFormat = ServiceLocator.getBean(Serializer.class);
        this.message = transportFormat.serialize(message);
    }

    public String getMessage() {
        return message;
    }

    public static TaskPossiblyChanged createNegotiationMessage(Object object) {
        return new TaskPossiblyChanged(object);
    }
}
