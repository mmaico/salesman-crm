package br.com.kproj.salesman.products_catalog.view.resources;

import br.com.kproj.salesman.products_catalog.domain.model.saleables.Product;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;


@ResourceItem(name="saleables", modelReference = Product.class)
public class ProductResource extends SaleableResource {

    private String type = "product";



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
