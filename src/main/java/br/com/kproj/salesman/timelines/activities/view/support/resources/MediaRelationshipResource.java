package br.com.kproj.salesman.timelines.activities.view.support.resources;


import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationship;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "activity",
        "media",
        "links"
})
@ResourceItem(name="activities-medias-relationships", modelReference = MediaRelationship.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaRelationshipResource extends Item {

    private Long id;
    private ActivityResource activity;
    private MediaResource media;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Selectable(expression = "activity", externalLink = true)
    public ActivityResource getActivity() {
        return activity;
    }

    public void setActivity(ActivityResource activity) {
        this.activity = activity;
    }

    @Selectable(expression = "media", externalLink = true)
    public MediaResource getMedia() {
        return media;
    }

    public void setMedia(MediaResource media) {
        this.media = media;
    }

    @JsonIgnore
    public Long getActivityId() {
        return activity != null ? activity.getId() : null;
    }

    @JsonIgnore
    public Long getMediaId() {
        return media != null ? media.getId() : null;
    }

}
