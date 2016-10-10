package br.com.kproj.salesman.products_catalog.view.support.resources;

import br.com.kproj.salesman.products_catalog.domain.model.saleables.Service;
import br.com.kproj.salesman.products_catalog.view.support.SaleableType;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;


@ResourceItem(name="saleables", modelReference = Service.class)
public class ServiceResource extends SaleableResource {

    private String type = SaleableType.SERVICE.getType();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
