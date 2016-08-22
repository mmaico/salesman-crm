package br.com.kproj.salesman.products_catalog.domain.model.unit;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.saleable.MeasurementUnitEntity;
import com.trex.shared.annotations.EntityReference;


@EntityReference(MeasurementUnitEntity.class)
public class Unit extends Identifiable {

    private Long id;
    private String name;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
