package br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.relations.Incident;
import com.trex.shared.annotations.Model;

@Model
public class ActivityIncident extends Activity {

    private Incident incident;

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }
}
