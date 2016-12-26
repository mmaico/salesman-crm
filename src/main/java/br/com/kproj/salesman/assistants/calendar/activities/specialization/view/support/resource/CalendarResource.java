package br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.resource;



import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.Activity;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collection;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="calendars", modelReference = Activity.class, parent = UserResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalendarResource extends Item {

    private Long id;

    private Collection<ActivityResource> activities;

    private UserResource owner;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Selectable(expression = "has-activities")
    public Collection<ActivityResource> getActivities() {
        return activities;
    }

    public void setActivities(Collection<ActivityResource> activities) {
        this.activities = activities;
    }

    @Selectable(expression = "owner", externalLink = true)
    public UserResource getOwner() {
        return owner;
    }

    public void setOwner(UserResource owner) {
        this.owner = owner;
    }

    public Long getOwnerId() {
        return this.owner == null ? null : this.owner.getId();
    }
}
