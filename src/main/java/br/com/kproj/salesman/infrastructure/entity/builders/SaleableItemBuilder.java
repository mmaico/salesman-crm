package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.negotiation.domain.model.negotiation.SaleableItem;
import br.com.kproj.salesman.negotiation.domain.model.product.Saleable;
import br.com.kproj.salesman.negotiation.domain.model.product.SaleablePackage;

import java.math.BigDecimal;

public class SaleableItemBuilder extends AbstractBuilder<SaleableItem>  {

	public SaleableItemBuilder() {
		this.entity = new SaleableItem();
	}

	public SaleableItemBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public SaleableItemBuilder withPrice(BigDecimal price) {
		this.entity.setPrice(price);
		return this;
	}

    public SaleableItemBuilder withOriginalPrice(BigDecimal originalPrice) {
        this.entity.setOriginalPrice(originalPrice);
        return this;
    }

    public SaleableItemBuilder withQuantity(Integer quantity) {
        this.entity.setQuantity(quantity);
        return this;
    }


    public SaleableItemBuilder withSaleable(Saleable saleable) {
        this.entity.setSaleable(saleable);
        return this;
    }

    public SaleableItemBuilder withPackage(SaleablePackage salePackageSaleable) {
        this.entity.setSaleablePackage(salePackageSaleable);
        return this;
    }


	public static SaleableItemBuilder createProposalSaleable(Long id) {
		return new SaleableItemBuilder(id);
	}

	public static SaleableItemBuilder create() {
		return new SaleableItemBuilder();
	}
}
