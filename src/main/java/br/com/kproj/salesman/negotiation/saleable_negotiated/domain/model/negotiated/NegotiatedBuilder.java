package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleablePackage;

import java.math.BigDecimal;

public class NegotiatedBuilder extends AbstractBuilder<Negotiated>  {

	public NegotiatedBuilder() {
		this.entity = new Negotiated();
	}

	public NegotiatedBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public NegotiatedBuilder withSaleable(Saleable saleable) {
		this.entity.setSaleable(saleable);
		return this;
	}

	public NegotiatedBuilder withPackage(SaleablePackage saleable) {
		this.entity.setUsedPackage(saleable);
		return this;
	}

	public NegotiatedBuilder withPrice(BigDecimal price) {
		this.entity.setPrice(price);
		return this;
	}

	public NegotiatedBuilder withOriginalPrice(BigDecimal originalPrice) {
		this.entity.setOriginalPrice(originalPrice);
		return this;
	}

	public NegotiatedBuilder withQuantity(Integer quantity) {
		this.entity.setQuantity(quantity);
		return this;
	}


	public static NegotiatedBuilder createSaleableItem(Long id) {
		return new NegotiatedBuilder(id);
	}

	public static NegotiatedBuilder createSaleableItem() {
		return new NegotiatedBuilder();
	}
}
