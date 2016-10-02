package br.com.kproj.salesman.sales.domain.model.system;


import br.com.kproj.salesman.sales.domain.model.negotiation.INegotiation;

public class Event {

    private final INegotiation object;

    public Event(INegotiation object) {
        this.object = object;
    }

    public INegotiation getObject() {
        return object;
    }

    public static Event event(INegotiation object) {
        return new Event(object);
    }
}
