package br.com.kproj.salesman.infrastructure.entity.saleable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "packages")
public class Package extends SaleableUnit {

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="package_saleable", joinColumns=@JoinColumn(name="package_id"),
            inverseJoinColumns=@JoinColumn(name="saleable_id"))
    private List<SaleableUnit> saleableUnits;

    public Package(Long id) {
        super(id);
    }

    public Package() {
        super();
    }

    public List<SaleableUnit> getSaleableUnits() {
        return saleableUnits;
    }

    public void setSaleableUnits(List<SaleableUnit> saleableUnits) {
        this.saleableUnits = saleableUnits;
    }
}
