package br.com.kproj.salesman.infrastructure.entity.saleable;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="services")
public class Service extends SaleableUnit {

    public Service() {
        super();
        setType(SaleableType.SERVICE);
    }

    public Service(Long id) {
        super(id);
        setType(SaleableType.SERVICE);
    }
}
