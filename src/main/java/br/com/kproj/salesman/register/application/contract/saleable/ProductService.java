package br.com.kproj.salesman.register.application.contract.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.Product;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface ProductService extends ModelService<Product> {


    Product register(Product product);
}
