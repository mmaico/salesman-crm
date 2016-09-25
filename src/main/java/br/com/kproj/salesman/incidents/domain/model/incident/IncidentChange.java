package br.com.kproj.salesman.incidents.domain.model.incident;


import br.com.kproj.salesman.incidents.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class IncidentChange implements ValueObject {


    private final Incident incident;
    private final User user;

    public IncidentChange(Incident incident, User user) {
        this.incident = incident;
        this.user = user;
    }

    public Incident getIncident() {
        return incident;
    }

    public User getUser() {
        return user;
    }

    public static IncidentChange createIncident(Incident incident, User user) {
        return new IncidentChange(incident, user);
    }
}
