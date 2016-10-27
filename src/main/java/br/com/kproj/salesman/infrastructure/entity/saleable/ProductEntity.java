package br.com.kproj.salesman.infrastructure.entity.saleable;

import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;

@Entity
@Table(name="products")
@DiscriminatorOptions(force = true)
@DiscriminatorValue("PRODUCT")
public class ProductEntity extends SaleableUnitEntity {

    @Id
    private Long id;

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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
