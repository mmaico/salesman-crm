package br.com.kproj.salesman.infrastructure.entity.saleable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class ProductEntity extends SaleableUnitEntity {

    @ManyToOne
    @JoinColumn(name="measurement_unit_id")
    private MeasurementUnitEntity measurementUnit;

    public ProductEntity() {
        super();
        setType(SaleableTypeEntity.PRODUCT);
    }

    public ProductEntity(Long id) {
        super(id);
        setType(SaleableTypeEntity.PRODUCT);
    }

    public MeasurementUnitEntity getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnitEntity measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
}
