package br.com.kproj.salesman.infrastructure.entity.sale;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name="sales_order_item")
public class SalesOrderItem extends Identifiable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3881704814612452364L;

    @Id
    @GeneratedValue
    private Long id;

	@ManyToOne
    @JoinColumn(name="saleable_id")
    private SaleableUnitEntity saleableUnit;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private SalePackageEntity salePackage;

    @NotNull(message = "sales.order.item.price.is.invalid")
    private BigDecimal price;

    @Column(name="original_price")
    private BigDecimal originalPrice;

    @NotNull
    @Min(value = 1, message = "sales.order.item.quantity.saleable.lessthan.one")
    private Integer quantity = 0;

    @ManyToOne
    @JoinColumn(name="sales_order_id")
    private SalesOrderEntity salesOrder;


    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaleableUnitEntity getSaleableUnit() {
        return saleableUnit;
    }

    public void setSaleableUnit(SaleableUnitEntity saleableUnit) {
        this.saleableUnit = saleableUnit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public SalePackageEntity getSalePackage() {
        return salePackage;
    }

    public void setSalePackage(SalePackageEntity salePackage) {
        this.salePackage = salePackage;
    }

    public SalesOrderEntity getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrderEntity salesOrder) {
        this.salesOrder = salesOrder;
    }

    public SaleableUnitEntity getSaleableAvailable() {
        return this.getSaleableUnit() != null ? this.getSaleableUnit() : this.getSalePackage();
    }
}
