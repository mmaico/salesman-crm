package br.com.kproj.salesman.products_catalog.delivery_definition.view.support.resources;

import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="saleables", modelReference = Saleable.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleableResource extends Item {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
