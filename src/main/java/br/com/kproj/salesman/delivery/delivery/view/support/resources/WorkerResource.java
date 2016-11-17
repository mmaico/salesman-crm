package br.com.kproj.salesman.delivery.delivery.view.support.resources;


import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="workers", modelReference = Worker.class, parent = DeliveryResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkerResource extends Item {

    private Long id;
    private DeliveryResource delivery;
    private UserResource user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "of-delivery", externalLink = true )
    public DeliveryResource getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryResource delivery) {
        this.delivery = delivery;
    }

    @Selectable(expression = "user", externalLink = true)
    public UserResource getUser() {
        return user;
    }

    public void setUser(UserResource user) {
        this.user = user;
    }
}
