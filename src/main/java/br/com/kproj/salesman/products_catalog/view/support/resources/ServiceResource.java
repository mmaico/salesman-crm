package br.com.kproj.salesman.products_catalog.view.support.resources;

import br.com.kproj.salesman.products_catalog.domain.model.saleables.Service;
import br.com.kproj.salesman.products_catalog.view.support.SaleableType;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import br.com.uol.rest.infrastructure.annotations.SuperClass;
import com.fasterxml.jackson.annotation.JsonProperty;


@ResourceItem(name="services", modelReference = Service.class, parent = SaleableResource.class)
public class ServiceResource extends SaleableResource {

    private Long id;

    @JsonProperty("IsA")
    @SuperClass
    private SaleableResource saleable;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "is-a")
    public SaleableResource getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableResource saleable) {
        this.saleable = saleable;
    }

}
