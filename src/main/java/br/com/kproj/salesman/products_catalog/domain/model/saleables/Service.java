package br.com.kproj.salesman.products_catalog.domain.model.saleables;

import br.com.kproj.salesman.infrastructure.entity.saleable.ServiceEntity;
import com.trex.shared.annotations.EntityReference;

@EntityReference(ServiceEntity.class)
public class Service extends SaleableUnit {

    public Service() {
        super();
    }

    public Service(Long id) {
        super(id);
    }
}
