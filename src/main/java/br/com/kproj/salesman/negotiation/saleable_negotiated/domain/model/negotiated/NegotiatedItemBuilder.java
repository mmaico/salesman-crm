package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleablePackage;

import java.math.BigDecimal;

public class NegotiatedItemBuilder extends AbstractBuilder<NegotiatedItem>  {

	public NegotiatedItemBuilder() {
		this.entity = new NegotiatedItem();
	}

	public NegotiatedItemBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public NegotiatedItemBuilder withSaleable(Saleable saleable) {
		this.entity.setSaleable(saleable);
		return this;
	}

	public NegotiatedItemBuilder withSaleablePackage(SaleablePackage saleable) {
		this.entity.setSaleablePackage(saleable);
		return this;
	}

	public NegotiatedItemBuilder withPrice(BigDecimal price) {
		this.entity.setPrice(price);
		return this;
	}

	public NegotiatedItemBuilder withOriginalPrice(BigDecimal originalPrice) {
		this.entity.setOriginalPrice(originalPrice);
		return this;
	}

	public NegotiatedItemBuilder withQuantity(Integer quantity) {
		this.entity.setQuantity(quantity);
		return this;
	}


	public static NegotiatedItemBuilder createSaleableItem(Long id) {
		return new NegotiatedItemBuilder(id);
	}

	public static NegotiatedItemBuilder createSaleableItem() {
		return new NegotiatedItemBuilder();
	}
}
