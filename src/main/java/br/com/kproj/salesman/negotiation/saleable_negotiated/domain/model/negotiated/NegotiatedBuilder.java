package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.Negotiation;

import java.math.BigDecimal;

public class NegotiatedBuilder extends AbstractBuilder<Negotiated>  {

	public NegotiatedBuilder() {
		this.entity = new Negotiated();
	}

	public NegotiatedBuilder(Long id) {
		this();
		this.entity.setId(id);
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

	public NegotiatedBuilder withNegotiation(Long negotiationId) {
		this.entity.setNegotiation(new Negotiation(negotiationId));
		return this;
	}

	public static NegotiatedBuilder createSaleableItem(Long id) {
		return new NegotiatedBuilder(id);
	}

	public static NegotiatedBuilder createSaleableItem() {
		return new NegotiatedBuilder();
	}
}
