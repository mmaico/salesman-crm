package br.com.kproj.salesman.infrastructure.entity.assistants.calendar;


import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="calendar_activity")
public class CalendarActivityEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name="calendar_id")
    @ExcludeField
    private CalendarEntity calendar;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date start;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date end;

    @Column(name="is_all_day")
    private Boolean allDay = Boolean.FALSE;

    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "represent")
    private CalendarActivityType represent;

    public CalendarActivityEntity(){}

    public CalendarActivityEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CalendarEntity getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarEntity calendar) {
        this.calendar = calendar;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public CalendarActivityType getRepresent() {
        return represent;
    }

    public void setRepresent(CalendarActivityType represent) {
        this.represent = represent;
    }
}
