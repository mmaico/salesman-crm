package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;

import java.math.BigDecimal;

public class SalesOrderItemBuilder extends AbstractBuilder<SalesOrderItem>  {

	public SalesOrderItemBuilder() {
		this.entity = new SalesOrderItem();
	}

	public SalesOrderItemBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static SalesOrderItemBuilder createSalesOrderItem(Long id) {
		return new SalesOrderItemBuilder(id);
	}

	public static SalesOrderItemBuilder createSalesOrderItem() {
		return new SalesOrderItemBuilder();
	}

    public SalesOrderItemBuilder withSaleable(SaleableUnitEntity saleableUnit) {
        this.entity.setSaleableUnit(saleableUnit);
        return this;
    }

    public SalesOrderItemBuilder withSalesPackage(SalePackageEntity salesPackage) {
        this.entity.setSalePackage(salesPackage);
        return this;
    }

    public SalesOrderItemBuilder withPrice(BigDecimal price) {
        this.entity.setPrice(price);
        return this;
    }

    public SalesOrderItemBuilder withOriginalPrice(BigDecimal originalPrice) {
        this.entity.setOriginalPrice(originalPrice);
        return this;
    }

    public SalesOrderItemBuilder withQuantity(Integer quantity) {
        this.entity.setQuantity(quantity);
        return this;
    }

    public SalesOrderItemBuilder withSalesOrder(SalesOrderEntity salesOrderEntity) {
        this.entity.setSalesOrderEntity(salesOrderEntity);
        return this;
    }

}
