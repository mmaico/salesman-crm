package br.com.kproj.salesman.products_catalog.view.support.resources;

import br.com.kproj.salesman.products_catalog.domain.model.saleables.Product;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonProperty;


@ResourceItem(name="saleables", modelReference = Product.class)
public class ProductResource extends Item {

    private Long id;

    @JsonProperty("hasUnit")
    private UnitResource unit;
    //@SuperClass
    private SaleableResource saleable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "has-unit", expandByDefault = true)
    public UnitResource getUnit() {
        return unit;
    }

    public void setUnit(UnitResource unit) {
        this.unit = unit;
    }

    @Selectable(expression = "is-a", expandByDefault = true)
    public SaleableResource getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableResource saleable) {
        this.saleable = saleable;
    }
}
