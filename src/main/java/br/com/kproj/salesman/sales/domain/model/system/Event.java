package br.com.kproj.salesman.sales.domain.model.system;


public class Event {

    private final Object object;

    public Event(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public static Event event(Object object) {
        return new Event(object);
    }
}
