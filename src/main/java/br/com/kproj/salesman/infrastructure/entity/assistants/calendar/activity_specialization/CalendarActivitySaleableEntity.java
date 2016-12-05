package br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;

import javax.persistence.*;

@Entity
@Table(name="calendar_activity_saleable")
public class CalendarActivitySaleableEntity extends Identifiable {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="saleable_id")
    private SaleableUnitEntity saleable;


    public CalendarActivitySaleableEntity(){}

    public SaleableUnitEntity getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableUnitEntity saleable) {
        this.saleable = saleable;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
