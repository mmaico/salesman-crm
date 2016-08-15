package br.com.kproj.salesman.products_catalog.domain.model.saleables;

import br.com.kproj.salesman.products_catalog.domain.model.unit.Unit;
import lombok.Data;

@Data
public class Product extends SaleableUnit {

    private Unit unit;

    public Product() {
        super();
    }

    public Product(Long id) {
        super(id);
    }



}
