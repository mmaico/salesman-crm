package br.com.kproj.salesman.administration.approval_negotiation.view.support.resources;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.user.User;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="rs/users", modelReference = User.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResource extends Item {

    private Long id;

    private Boolean available = Boolean.FALSE;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
