package br.com.kproj.salesman.infrastructure.entity.saleable;


import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="services")
@DiscriminatorOptions(force = true)
@DiscriminatorValue("SERVICE")
public class ServiceEntity extends SaleableUnitEntity {

    public ServiceEntity() {
        super();
        setType(SaleableTypeEntity.SERVICE);
    }

    public ServiceEntity(Long id) {
        super(id);
        setType(SaleableTypeEntity.SERVICE);
    }
}
