package br.com.kproj.salesman.infrastructure.entity.saleable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product extends SaleableUnit {

    @ManyToOne
    @JoinColumn(name="measurement_unit_id")
    private MeasurementUnit measurementUnit;

    public Product() {
        super();
        setType(SaleableType.PRODUCT);
    }

    public Product(Long id) {
        super(id);
        setType(SaleableType.PRODUCT);
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
}
