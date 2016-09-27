package br.com.kproj.salesman.infrastructure.events;


import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.helpers.transportformat.TransportFormat;

public class TaskPossiblyChanged {

    private final String message;


    public TaskPossiblyChanged(String message) {
        this.message = message;
    }

    public TaskPossiblyChanged(Object message) {
        TransportFormat transportFormat = ServiceLocator.getBean(TransportFormat.class);
        this.message = transportFormat.format(message);
    }

    public String getMessage() {
        return message;
    }

    public static TaskPossiblyChanged createNegotiationMessage(Object object) {
        return new TaskPossiblyChanged(object);
    }
}
