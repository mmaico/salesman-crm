package br.com.kproj.salesman.negotiation2.domain.model.payment;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.SaleableItem;
import br.com.kproj.salesman.negotiation2.domain.model.product.Saleable;
import br.com.kproj.salesman.negotiation2.domain.model.product.SaleablePackage;

import java.math.BigDecimal;
import java.util.Date;

public class InstallmentItemBuilder extends AbstractBuilder<InstallmentItem>  {

	public InstallmentItemBuilder() {
		this.entity = new InstallmentItem();
	}

	public InstallmentItemBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public InstallmentItemBuilder withDueDate(Date date) {
		this.entity.setDueDate(date);
		return this;
	}

	public InstallmentItemBuilder withValue(BigDecimal value) {
		this.entity.setValue(value);
		return this;
	}

	public InstallmentItemBuilder withObservation(String observation) {
		this.entity.setObservation(observation);
		return this;
	}


	public static InstallmentItemBuilder createSaleableItem(Long id) {
		return new InstallmentItemBuilder(id);
	}

	public static InstallmentItemBuilder createSaleableItem() {
		return new InstallmentItemBuilder();
	}
}
