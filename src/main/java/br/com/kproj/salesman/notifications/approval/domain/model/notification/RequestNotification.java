package br.com.kproj.salesman.notifications.approval.domain.model.notification;


import br.com.kproj.salesman.infrastructure.model.ValueObject;
import br.com.kproj.salesman.notifications.approval.domain.model.negotiation.Negotiation;

public class RequestNotification implements ValueObject {

    private final Negotiation negotiation;
    private final Receivers receivers;


    public RequestNotification(Negotiation negotiation, Receivers receivers) {
        this.negotiation = negotiation;
        this.receivers = receivers;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public Receivers getReceivers() {
        return receivers;
    }

    public static RequestNotification createRequest(Negotiation negotiation, Receivers receivers) {
        return new RequestNotification(negotiation, receivers);
    }
}
