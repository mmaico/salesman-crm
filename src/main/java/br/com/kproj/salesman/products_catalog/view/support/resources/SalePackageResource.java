package br.com.kproj.salesman.products_catalog.view.support.resources;

import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.view.support.SaleableType;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.Collection;

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
@ResourceItem(name="saleables", modelReference = SalePackage.class)
public class SalePackageResource extends Item {

    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private BigDecimal priceCost;
    private String type = SaleableType.SALE_PACKAGE.getType();

    @JsonProperty("hasSaleables")
    private Collection<SaleableResource> saleables;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Selectable(expression = "has-saleables", expandByDefault = true)
    public Collection<SaleableResource> getSaleables() {
        return saleables;
    }

    public void setSaleables(Collection<SaleableResource> saleables) {
        this.saleables = saleables;
    }
}
