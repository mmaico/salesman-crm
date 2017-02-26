package br.com.kproj.salesman.timelines.activities.view.support.resources;


import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Tag;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({
        "id",
        "description",
        "creation",
        "status",
        "timeline",
        "links"
})
@ResourceItem(name="activities", modelReference = Activity.class, parent = TimelineResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityResource extends Item {

    private Long id;
    private String description;
    private Date creation;
    private Tag tag;
    private UserResource user;
    private TimelineResource timeline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Selectable(expression = "of-timeline", externalLink = true)
    public TimelineResource getTimeline() {
        return timeline;
    }

    public void setTimeline(TimelineResource timeline) {
        this.timeline = timeline;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Selectable(expression = "of-user", externalLink = true)
    public UserResource getUser() {
        return user;
    }

    public void setUser(UserResource user) {
        this.user = user;
    }
}
