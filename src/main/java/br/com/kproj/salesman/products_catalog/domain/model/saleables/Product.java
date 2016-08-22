package br.com.kproj.salesman.products_catalog.domain.model.saleables;

import br.com.kproj.salesman.infrastructure.entity.saleable.MeasurementUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.products_catalog.domain.model.unit.Unit;
import com.trex.shared.annotations.EntityReference;


@EntityReference(ProductEntity.class)
public class Product extends SaleableUnit {

    @EntityReference(value = MeasurementUnitEntity.class, fieldName = "measurementUnit")
    private Unit unit;

    public Product() {
        super();
    }

    public Product(Long id) {
        super(id);
    }


    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
