package br.com.kproj.salesman.infrastructure.entity.sale;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;

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
    @NotNull(message = "sales.order.item.is.invalid")
    private SaleableUnit saleableUnit;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private SalePackage salePackage;

    @NotNull(message = "sales.order.item.price.is.invalid")
    private BigDecimal price;

    @Column(name="original_price")
    private BigDecimal originalPrice;

    @NotNull
    @Min(value = 1, message = "sales.order.item.quantity.saleable.lessthan.one")
    private Integer quantity = 0;

    @ManyToOne
    @JoinColumn(name="sales_order_id")
    private SalesOrder salesOrder;


    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaleableUnit getSaleableUnit() {
        return saleableUnit;
    }

    public void setSaleableUnit(SaleableUnit saleableUnit) {
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

    public SalePackage getSalePackage() {
        return salePackage;
    }

    public void setSalePackage(SalePackage salePackage) {
        this.salePackage = salePackage;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }
}
