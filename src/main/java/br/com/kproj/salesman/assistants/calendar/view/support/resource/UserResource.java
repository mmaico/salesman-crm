package br.com.kproj.salesman.assistants.calendar.view.support.resource;


import br.com.kproj.salesman.assistants.calendar.domain.model.user.User;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="users", modelReference = User.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResource extends Item {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
