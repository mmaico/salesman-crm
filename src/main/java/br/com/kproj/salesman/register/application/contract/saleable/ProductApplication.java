package br.com.kproj.salesman.register.application.contract.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface ProductApplication extends ModelLegacyService<ProductEntity> {


    ProductEntity register(ProductEntity product);
}
