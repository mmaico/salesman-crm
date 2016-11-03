package br.com.kproj.salesman.products_catalog.catalog.view.support.resources;

import br.com.kproj.salesman.products_catalog.catalog.domain.model.services.Service;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import br.com.uol.rest.infrastructure.annotations.SuperClass;


@ResourceItem(name="services", modelReference = Service.class, parent = SaleableResource.class)
public class ServiceResource extends Item {

    private Long id;

    @SuperClass
    private SaleableResource saleable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "is-a", expandByDefault = true)
    public SaleableResource getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableResource saleable) {
        this.saleable = saleable;
    }

}
