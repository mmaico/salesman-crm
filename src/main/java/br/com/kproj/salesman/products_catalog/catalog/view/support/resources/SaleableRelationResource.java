package br.com.kproj.salesman.products_catalog.catalog.view.support.resources;

import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SaleableRelation;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "active",
        "price",
        "priceCost",
        "links"
})
@ResourceItem(name="relations", modelReference = SaleableRelation.class, parent = SalePackageResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleableRelationResource extends Item {

    private Long id;
    private SaleableResource saleable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaleableResource getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableResource saleable) {
        this.saleable = saleable;
    }
}
