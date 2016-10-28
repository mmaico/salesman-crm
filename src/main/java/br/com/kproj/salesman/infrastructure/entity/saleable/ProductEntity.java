package br.com.kproj.salesman.infrastructure.entity.saleable;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name="products")
public class ProductEntity extends Identifiable {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="measurement_unit_id")
    private MeasurementUnitEntity measurementUnit;

    public MeasurementUnitEntity getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnitEntity measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
