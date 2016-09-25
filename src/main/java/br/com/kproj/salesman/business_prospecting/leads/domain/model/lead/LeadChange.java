package br.com.kproj.salesman.business_prospecting.leads.domain.model.lead;

import br.com.kproj.salesman.business_prospecting.leads.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class LeadChange implements ValueObject {

    private final Lead lead;
    private final User user;

    public LeadChange(Lead lead, User user) {
        this.lead = lead;
        this.user = user;
    }


    public Lead getLead() {
        return lead;
    }

    public User getUser() {
        return user;
    }
}
