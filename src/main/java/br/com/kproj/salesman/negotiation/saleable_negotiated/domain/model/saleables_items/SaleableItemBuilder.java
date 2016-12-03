package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleablePackage;

public class SaleableItemBuilder extends AbstractBuilder<SaleableItem>  {

	public SaleableItemBuilder() {
		this.entity = new SaleableItem();
	}

	public SaleableItemBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public SaleableItemBuilder withSaleable(Long saleableId) {
		this.entity.setSaleable(new Saleable(saleableId));
		return this;
	}

	public SaleableItemBuilder withUsedPackage(Long packageId) {
		this.entity.setUsedPackage(new SaleablePackage(packageId));
		return this;
	}

	public SaleableItemBuilder withNegotiated(Long negotiatedId) {
		this.entity.setNegotiated(new Negotiated(negotiatedId));
		return this;
	}

	public static SaleableItemBuilder createSaleableItem(Long id) {
		return new SaleableItemBuilder(id);
	}

	public static SaleableItemBuilder createSaleableItem() {
		return new SaleableItemBuilder();
	}
}
