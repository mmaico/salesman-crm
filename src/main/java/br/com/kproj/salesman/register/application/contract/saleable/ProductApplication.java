package br.com.kproj.salesman.register.application.contract.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface ProductApplication extends ModelService<ProductEntity> {


    ProductEntity register(ProductEntity product);
}
