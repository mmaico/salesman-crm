package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class SaleableBuilder extends AbstractBuilder<Saleable>  {

	public SaleableBuilder() {
		this.entity = new Saleable();
	}

	public SaleableBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static SaleableBuilder createSaleable(Long id) {
		return new SaleableBuilder(id);
	}

	public static SaleableBuilder createSaleable() {
		return new SaleableBuilder();
	}
}
