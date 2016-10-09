package br.com.kproj.salesman.products_catalog.view.resources;

import br.com.kproj.salesman.products_catalog.domain.model.saleables.Service;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;


@ResourceItem(name="saleables", modelReference = Service.class)
public class ServiceResource extends SaleableResource {

    private String type = "service";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
