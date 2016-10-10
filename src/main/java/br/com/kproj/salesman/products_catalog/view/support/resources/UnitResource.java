package br.com.kproj.salesman.products_catalog.view.support.resources;

import br.com.kproj.salesman.products_catalog.domain.model.unit.Unit;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name",
        "links"
})
@ResourceItem(name="units", modelReference = Unit.class, parent = SaleableResource.class)
public class UnitResource extends Item {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
