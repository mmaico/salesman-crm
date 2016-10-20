package br.com.kproj.salesman.products_catalog.view.support.resources;

import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackage;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collection;

@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "active",
        "price",
        "priceCost",
        "type",
        "unit",
        "links"
})
@ResourceItem(name="saleables", modelReference = SalePackage.class)
public class SalePackageResource extends Item {

    private Long id;

    @JsonProperty("IsA")
    private SaleableResource saleable;

    @JsonProperty("hasSaleables")
    private Collection<SaleableResource> saleables;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "has-saleables", expandByDefault = true)
    public Collection<SaleableResource> getSaleables() {
        return saleables;
    }

    public void setSaleables(Collection<SaleableResource> saleables) {
        this.saleables = saleables;
    }

    @Selectable(expression = "is-a", expandByDefault = true)
    public SaleableResource getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableResource saleable) {
        this.saleable = saleable;
    }

}
