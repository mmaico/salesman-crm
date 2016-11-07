package br.com.kproj.salesman.infrastructure.entity.saleable;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name="saleable_relation")
public class SaleableRelationEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "saleable_id")
    private SaleableUnitEntity saleable;

    @ManyToOne
    @JoinColumn(name = "salepackage_id")
    private SalePackageEntity salePackage;

    public SaleableRelationEntity() {}

    public SaleableRelationEntity(Long id) {
        this.id = id;
    }

    public SaleableRelationEntity(SaleableUnitEntity saleable, SalePackageEntity salePackage) {
        this.saleable = saleable;
        this.salePackage = salePackage;
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

    public SalePackageEntity getSalePackage() {
        return salePackage;
    }

    public void setSalePackage(SalePackageEntity salePackage) {
        this.salePackage = salePackage;
    }
}
