package br.com.kproj.salesman.timelines.activities.view.support.resources;


import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationship;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="rs/storages/medias", modelReference = MediaRelationship.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaResource extends Item {

    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
