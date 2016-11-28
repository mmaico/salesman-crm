package br.com.kproj.salesman.negotiation.negotiation.domain.model.seller;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class SellerBuilder extends AbstractBuilder<Seller>  {

	public SellerBuilder() {
		this.entity = new Seller();
	}

	public SellerBuilder(Long id) {
		this();
		this.entity.setId(id);
	}



	public static SellerBuilder createSeller(Long id) {
		return new SellerBuilder(id);
	}

	public static SellerBuilder createSeller() {
		return new SellerBuilder();
	}
}
