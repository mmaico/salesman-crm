package br.com.kproj.salesman.medias.media.view.support.resources;



import br.com.kproj.salesman.medias.media.domain.storage.Storage;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name",
        "links"
})
@ResourceItem(name="rs/storages", modelReference = Storage.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageResource extends Item {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
