package br.com.kproj.salesman.infrastructure.entity.saleable;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="services")
public class ServiceEntity extends Identifiable {

    @Id
    private Long id;

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
}
