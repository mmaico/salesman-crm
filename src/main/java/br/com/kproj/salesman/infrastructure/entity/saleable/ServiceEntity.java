package br.com.kproj.salesman.infrastructure.entity.saleable;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name="services")
public class ServiceEntity extends Identifiable {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "saleable_id")
    private SaleableUnitEntity saleable;

    public ServiceEntity() {}

    public ServiceEntity(Long id) {
        this.id = id;

    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaleableUnitEntity getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableUnitEntity saleable) {
        this.saleable = saleable;
    }
}
