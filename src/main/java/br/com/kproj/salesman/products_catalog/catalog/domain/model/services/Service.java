package br.com.kproj.salesman.products_catalog.catalog.domain.model.services;

import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import com.trex.shared.annotations.Model;

@Model
public class Service extends SaleableUnit {

    public Service() {
        super();
    }

    public Service(Long id) {
        super(id);
    }
}
