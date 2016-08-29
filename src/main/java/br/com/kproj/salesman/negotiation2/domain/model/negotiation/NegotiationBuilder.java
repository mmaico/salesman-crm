package br.com.kproj.salesman.negotiation2.domain.model.negotiation;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.negotiation2.domain.model.account.Account;
import br.com.kproj.salesman.negotiation2.domain.model.operationregion.OperationRegion;
import br.com.kproj.salesman.negotiation2.domain.model.payment.InstallmentItem;
import br.com.kproj.salesman.negotiation2.domain.model.seller.Seller;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class NegotiationBuilder extends AbstractBuilder<Negotiation>  {

	public NegotiationBuilder() {
		this.entity = new Negotiation();
	}

	public NegotiationBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public NegotiationBuilder withAccount(Account account) {
		this.entity.setAccount(account);
		return this;
	}

	public NegotiationBuilder withSeller(Seller seller) {
		this.entity.setSeller(seller);
		return this;
	}

	public NegotiationBuilder withOperation(OperationRegion operation) {
		this.entity.setOperationRegion(operation);
		return this;
	}

	public NegotiationBuilder withDeliveryForeCast(Date date) {
		this.entity.setDeliveryForeCast(date);
		return this;
	}

	public NegotiationBuilder withIntroduction(String description) {
		this.entity.setIntroduction(description);
		return this;
	}

	public NegotiationBuilder withCareOf(String careOf) {
		this.entity.setCareOf(careOf);
		return this;
	}

	public NegotiationBuilder withTemperature(Temperature temperature) {
		this.entity.setTemperature(temperature);
		return this;
	}

	public NegotiationBuilder addSaleableItem(SaleableItem saleableItem) {
		if (this.entity.getSaleablesItems() == null) {
			this.entity.setSaleablesItems(Lists.newArrayList());
		}
		this.entity.getSaleablesItems().add(saleableItem);
		return this;
	}

	public NegotiationBuilder withSaleableItem(List<SaleableItem> saleableItems) {
		this.entity.setSaleablesItems(saleableItems);
		return this;
	}


	public NegotiationBuilder addInstallments(InstallmentItem item) {
		if (this.entity.getInstallments() == null) {
			this.entity.setInstallments(Lists.newArrayList());
		}
		this.entity.getInstallments().add(item);
		return this;
	}

	public NegotiationBuilder withInstallments(List<InstallmentItem> items) {
		this.entity.setInstallments(items);
		return this;
	}


	public static NegotiationBuilder createNegotiation(Long id) {
		return new NegotiationBuilder(id);
	}

	public static NegotiationBuilder createNegotiation() {
		return new NegotiationBuilder();
	}
}
