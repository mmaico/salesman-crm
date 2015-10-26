package br.com.kproj.salesman.infrastructure.entity.saleable;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="measurement_unit")
public class MeasurementUnit extends Identifiable {

    @Id
    private Long id;

    private String name;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
