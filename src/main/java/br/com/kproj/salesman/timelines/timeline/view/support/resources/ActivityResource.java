package br.com.kproj.salesman.timelines.timeline.view.support.resources;


import br.com.kproj.salesman.timelines.timeline.domain.model.activities.Activity;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="activities", modelReference = Activity.class, parent = TimelineResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityResource extends Item {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
