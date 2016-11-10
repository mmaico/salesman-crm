package br.com.kproj.salesman.products_catalog.delivery_definition.view.support.resources;

import br.com.kproj.salesman.products_catalog.catalog.view.support.resources.SaleableResource;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.Region;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="task-definitions", modelReference = Region.class, parent = SaleableResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionResource extends Item {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
