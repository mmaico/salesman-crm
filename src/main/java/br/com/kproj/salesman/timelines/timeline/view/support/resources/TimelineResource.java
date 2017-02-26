package br.com.kproj.salesman.timelines.timeline.view.support.resources;


import br.com.kproj.salesman.timelines.timeline.domain.model.timeline.Timeline;
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
@ResourceItem(name="rs/timelines", modelReference = Timeline.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimelineResource extends Item {

    private Long id;

    private Collection<ActivityResource> activities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Selectable(expression = "has-activities", externalLink = true)
    public Collection<ActivityResource> getActivities() {
        return activities;
    }

    public void setWorkers(Collection<ActivityResource> activities) {
        this.activities = activities;
    }


}
