package br.com.kproj.salesman.infrastructure.entity.saleable;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "packages")
public class SalePackageEntity extends Identifiable {

    @Id
    private Long id;

//    @ManyToMany(fetch=FetchType.LAZY)
//    @JoinTable(name="package_saleable", joinColumns=@JoinColumn(name="package_id"),
//            inverseJoinColumns=@JoinColumn(name="saleable_id"))
//    private List<SaleableUnitEntity> saleableUnits;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "salePackage")
    private List<SaleableRelationEntity> relations;

    @Column(name="price_by_products")
    private Boolean priceByProducts = Boolean.FALSE;

    @OneToOne
    @JoinColumn(name = "saleable_id")
    private SaleableUnitEntity saleable;

    public SalePackageEntity(Long id) {
        this.id = id;
    }

    public SalePackageEntity() {}

//    public List<SaleableUnitEntity> getSaleableUnits() {
//        return saleableUnits;
//    }
//
//    public void setSaleableUnits(List<SaleableUnitEntity> saleableUnits) {
//        this.saleableUnits = saleableUnits;
//    }

    public Boolean getPriceByProducts() {
        return priceByProducts;
    }

    public Boolean calcPriceByProducts() {
        return priceByProducts == null ? Boolean.FALSE : priceByProducts;
    }

    public void setPriceByProducts(Boolean priceByProducts) {
        this.priceByProducts = priceByProducts;
    }

    public void addSaleableUnit(SaleableUnitEntity saleableUnit) {
        if (this.relations == null) {
            this.relations = Lists.newArrayList();
        }
        this.relations.add(new SaleableRelationEntity(saleableUnit, this));
    }
//
//    public void removeSaleableUnit(SaleableUnitEntity saleableUnit) {
//        if (this.saleableUnits == null) {
//            return;
//        }
//
//        this.saleableUnits.remove(saleableUnit);
//    }

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

    public List<SaleableRelationEntity> getRelations() {
        return relations;
    }

    public void setRelations(List<SaleableRelationEntity> relations) {
        this.relations = relations;
    }
}
