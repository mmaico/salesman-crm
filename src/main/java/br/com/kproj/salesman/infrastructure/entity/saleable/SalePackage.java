package br.com.kproj.salesman.infrastructure.entity.saleable;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "packages")
public class SalePackage extends SaleableUnit {

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="package_saleable", joinColumns=@JoinColumn(name="package_id"),
            inverseJoinColumns=@JoinColumn(name="saleable_id"))
    private Set<SaleableUnit> saleableUnits;

    public SalePackage(Long id) {
        super(id);
        setType(SaleableType.PACKAGE);
    }

    public SalePackage() {
        super();
        setType(SaleableType.PACKAGE);
    }

    public Set<SaleableUnit> getSaleableUnits() {
        return saleableUnits;
    }

    public void setSaleableUnits(Set<SaleableUnit> saleableUnits) {
        this.saleableUnits = saleableUnits;
    }

    public void addSaleableUnit(SaleableUnit saleableUnit) {
        if (this.saleableUnits == null) {
            this.saleableUnits = Sets.newHashSet();
        }
        this.saleableUnits.add(saleableUnit);
    }

    public void removeSaleableUnit(SaleableUnit saleableUnit) {
        if (this.saleableUnits == null) {
            return;
        }

        this.saleableUnits.remove(saleableUnit);
    }
}