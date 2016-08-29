package br.com.kproj.salesman.negotiation2.domain.model.negotiation;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.negotiation2.domain.model.product.Saleable;
import br.com.kproj.salesman.negotiation2.domain.model.product.SaleablePackage;

import java.math.BigDecimal;

public class SaleableItemBuilder extends AbstractBuilder<SaleableItem>  {

	public SaleableItemBuilder() {
		this.entity = new SaleableItem();
	}

	public SaleableItemBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public SaleableItemBuilder withSaleable(Saleable saleable) {
		this.entity.setSaleable(saleable);
		return this;
	}

	public SaleableItemBuilder withSaleablePackage(SaleablePackage saleable) {
		this.entity.setSaleablePackage(saleable);
		return this;
	}

	public SaleableItemBuilder withPrice(BigDecimal price) {
		this.entity.setPrice(price);
		return this;
	}

	public SaleableItemBuilder withOriginalPrice(BigDecimal originalPrice) {
		this.entity.setOriginalPrice(originalPrice);
		return this;
	}

	public SaleableItemBuilder withQuantity(Integer quantity) {
		this.entity.setQuantity(quantity);
		return this;
	}


	public static SaleableItemBuilder createSaleableItem(Long id) {
		return new SaleableItemBuilder(id);
	}

	public static SaleableItemBuilder createSaleableItem() {
		return new SaleableItemBuilder();
	}
}
