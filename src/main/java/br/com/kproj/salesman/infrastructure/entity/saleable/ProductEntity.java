package br.com.kproj.salesman.infrastructure.entity.saleable;

import lombok.Data;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;

@Entity
@Table(name="products")
@DiscriminatorOptions(force = true)
@DiscriminatorValue("PRODUCT")
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
