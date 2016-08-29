package br.com.kproj.salesman.negotiation.domain.model.product;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import com.google.common.collect.Lists;

import java.math.BigDecimal;

public class SaleablePackageBuilder extends AbstractBuilder<SaleablePackage>  {

	public SaleablePackageBuilder() {
		this.entity = new SaleablePackage();
	}

	public SaleablePackageBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public SaleablePackageBuilder withPrice(BigDecimal price) {
		this.entity.setPrice(price);
		return this;
	}

	public SaleablePackageBuilder addSaleable(Saleable saleable) {
		if (this.entity.getSaleables() == null) {
			this.entity.setSaleables(Lists.newArrayList());
		}
		this.entity.getSaleables().add(saleable);
		return this;
	}


	public static SaleablePackageBuilder createPackage(Long id) {
		return new SaleablePackageBuilder(id);
	}

	public static SaleablePackageBuilder createPackage() {
		return new SaleablePackageBuilder();
	}
}
