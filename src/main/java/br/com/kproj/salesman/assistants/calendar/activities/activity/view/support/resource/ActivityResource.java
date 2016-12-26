package br.com.kproj.salesman.assistants.calendar.activities.activity.view.support.resource;



import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.Activity;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "location",
        "start",
        "end",
        "allDay",
        "links"
})
@ResourceItem(name="calendar-activities", modelReference = Activity.class, parent = CalendarResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityResource extends Item {

    private Long id;
    private String title;
    private String description;
    private String location;

    @JsonFormat(timezone = "GMT-2")
    private Date start;

    @JsonFormat(timezone = "GMT-2")
    private Date end;
    private Boolean allDay = Boolean.FALSE;

    private CalendarResource calendar;


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

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    @Selectable(expression = "of-calendar", externalLink = true)
    public CalendarResource getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarResource calendar) {
        this.calendar = calendar;
    }
}
