package br.com.kproj.salesman.infrastructure.entity.saleable;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="services")
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
