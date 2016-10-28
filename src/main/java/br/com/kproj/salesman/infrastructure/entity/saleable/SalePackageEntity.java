package br.com.kproj.salesman.infrastructure.entity.saleable;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import com.google.common.collect.Lists;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "packages")
public class SalePackageEntity extends Identifiable {

    @Id
    private Long id;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="package_saleable", joinColumns=@JoinColumn(name="package_id"),
            inverseJoinColumns=@JoinColumn(name="saleable_id"))
    private List<SaleableUnitEntity> saleableUnits;

    @Column(name="price_by_products")
    private Boolean priceByProducts = Boolean.FALSE;

    public SalePackageEntity(Long id) {
        this.id = id;
    }

    public SalePackageEntity() {}

    public List<SaleableUnitEntity> getSaleableUnits() {
        return saleableUnits;
    }

    public void setSaleableUnits(List<SaleableUnitEntity> saleableUnits) {
        this.saleableUnits = saleableUnits;
    }

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
        if (this.saleableUnits == null) {
            this.saleableUnits = Lists.newArrayList();
        }
        this.saleableUnits.add(saleableUnit);
    }

    public void removeSaleableUnit(SaleableUnitEntity saleableUnit) {
        if (this.saleableUnits == null) {
            return;
        }

        this.saleableUnits.remove(saleableUnit);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
