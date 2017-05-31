package br.com.kproj.salesman.administration.approval_negotiation.view.support.resources;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="approvers", modelReference = Approver.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApproverResource extends Item {

    private Long id;

    private UserResource user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "user")
    public UserResource getUser() {
        return user;
    }

    public void setUser(UserResource user) {
        this.user = user;
    }

    @JsonIgnore
    public Long getUserId() {
        return user == null ? null : user.getId();
    }
}
