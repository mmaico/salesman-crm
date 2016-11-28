package br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.customer.Customer;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.operation.Region;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.Seller;

import java.math.BigDecimal;
import java.util.Date;

public class NegotiationBuilder extends AbstractBuilder<Negotiation>  {

	public NegotiationBuilder() {
		this.entity = new Negotiation();
	}

	public NegotiationBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public NegotiationBuilder withCustomer(Customer account) {
		this.entity.setCustomer(account);
		return this;
	}

	public NegotiationBuilder withCustomer(Long customerId) {
		this.entity.setCustomer(new Customer(customerId));
		return this;
	}

	public NegotiationBuilder withSeller(Seller seller) {
		this.entity.setSeller(seller);
		return this;
	}

	public NegotiationBuilder withSeller(Long sellerId) {
		if (sellerId != null) {
			this.entity.setSeller(new Seller(sellerId));
		}
		return this;
	}

	public NegotiationBuilder withRegion(Region operation) {
		this.entity.setRegion(operation);
		return this;
	}

	public NegotiationBuilder withRegion(Long regionId) {
		if (regionId != null) {
			this.entity.setRegion(new Region(regionId));
		}
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

	public NegotiationBuilder withDiscount(BigDecimal discount) {
		this.entity.setDiscount(discount);
		return this;
	}

	public NegotiationBuilder withAmmountPayable(BigDecimal discount) {
		this.entity.setAmmountPayable(discount);
		return this;
	}

	public NegotiationBuilder withTemperature(Temperature temperature) {
		this.entity.setTemperature(temperature);
		return this;
	}

	public static NegotiationBuilder createNegotiation(Long id) {
		return new NegotiationBuilder(id);
	}

	public static NegotiationBuilder createNegotiation() {
		return new NegotiationBuilder();
	}
}
