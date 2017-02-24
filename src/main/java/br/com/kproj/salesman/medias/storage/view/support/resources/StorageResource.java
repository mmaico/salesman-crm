package br.com.kproj.salesman.medias.storage.view.support.resources;


import br.com.kproj.salesman.medias.storage.domain.model.definition.Storage;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name",
        "links"
})
@ResourceItem(name="storages", modelReference = Storage.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageResource extends Item {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
