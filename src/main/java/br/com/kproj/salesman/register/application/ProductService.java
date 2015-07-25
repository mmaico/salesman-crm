package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.Product;
import br.com.kproj.salesman.infrastructure.service.ModelService;


public interface ProductService extends ModelService<Product> {

    Product register(Product product);
}
