package br.com.kproj.salesman.delivery.tasks.view.support.resources;


import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="deliveries", modelReference = Delivery.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryResource extends Item {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
