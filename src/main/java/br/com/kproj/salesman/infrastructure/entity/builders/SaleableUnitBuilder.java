package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;

import java.math.BigDecimal;

public class SaleableUnitBuilder extends AbstractBuilder<SaleableUnit>  {

	public SaleableUnitBuilder() {
		this.entity = new SaleableUnit();
	}

	public SaleableUnitBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public SaleableUnitBuilder withPrice(BigDecimal price) {
		this.entity.setPrice(price);
		return this;
	}

	public static SaleableUnitBuilder createSaleableUnit(Long id) {
		return new SaleableUnitBuilder(id);
	}

	public static SaleableUnitBuilder createSaleableUnit() {
		return new SaleableUnitBuilder();
	}
}
