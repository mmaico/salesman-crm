package br.com.kproj.salesman.products_catalog.catalog.domain.model.products;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.unit.Unit;

public class ProductBuilder extends AbstractBuilder<Product>  {

	public ProductBuilder() {
		this.entity = new Product();
	}

	public ProductBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public ProductBuilder withUnit(Long id) {
		this.entity.setUnit(new Unit(id));
		return this;
	}


	public static ProductBuilder createProduct(Long id) {
		return new ProductBuilder(id);
	}

}
