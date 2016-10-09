package br.com.kproj.salesman.products_catalog.view.resources;

import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "active",
        "priceCost",
        "type",
        "unit",
        "links"
})
@ResourceItem(name="saleables", modelReference = SaleableUnit.class)
public class SaleableResource extends Item {

    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private BigDecimal priceCost;

    @JsonProperty("hasUnit")
    private UnitResource unit;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BigDecimal getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(BigDecimal priceCost) {
        this.priceCost = priceCost;
    }

    @Selectable(expression = "has-unit", expandByDefault = true)
    public UnitResource getUnit() {
        return unit;
    }

    public void setUnit(UnitResource unit) {
        this.unit = unit;
    }

}
