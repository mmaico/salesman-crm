package br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.IncidentEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;

import javax.persistence.*;

@Entity
@Table(name="calendar_activity_incident")
public class CalendarActivityIncidentEntity extends Identifiable {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="incident_id")
    private IncidentEntity incident;

    @OneToOne
    @JoinColumn(name = "activity_id")
    private CalendarActivityEntity activity;

    public CalendarActivityIncidentEntity(){}

    public IncidentEntity getIncident() {
        return incident;
    }

    public void setIncident(IncidentEntity incident) {
        this.incident = incident;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CalendarActivityEntity getActivity() {
        return activity;
    }

    public void setActivity(CalendarActivityEntity activity) {
        this.activity = activity;
    }
}
