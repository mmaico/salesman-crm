package br.com.kproj.salesman.delivery.tasks.view.support.resources;

import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="sales-orders", modelReference = SalesOrder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesOrderResource extends Item {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
