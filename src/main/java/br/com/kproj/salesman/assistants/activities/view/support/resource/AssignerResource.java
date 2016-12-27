package br.com.kproj.salesman.assistants.activities.view.support.resource;


import br.com.kproj.salesman.assistants.activities.domain.model.user.Assigner;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="users", modelReference = Assigner.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignerResource extends Item {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
