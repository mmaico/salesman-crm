package br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity;




import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

import java.util.Date;

@Model
public class Activity extends ModelIdentifiable {

    private Long id;
    private String title;
    private String description;
    private String location;
    private Date start;
    private Date end;
    private Boolean allDay = Boolean.FALSE;
    private Represent represent;
    private Calendar calendar;


    @Override
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public Boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public Represent getRepresent() {
        return represent;
    }

    public void setRepresent(Represent represent) {
        this.represent = represent;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
