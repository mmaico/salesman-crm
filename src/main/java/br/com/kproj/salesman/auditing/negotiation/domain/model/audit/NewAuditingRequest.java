package br.com.kproj.salesman.auditing.negotiation.domain.model.audit;


import br.com.kproj.salesman.auditing.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.auditing.negotiation.domain.model.user.User;

public class NewAuditingRequest {

    private final User user;
    private final Negotiation negotiation;

    public NewAuditingRequest(User user, Negotiation negotiation) {
        this.user = user;
        this.negotiation = negotiation;
    }

    public User getUser() {
        return user;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }
}
