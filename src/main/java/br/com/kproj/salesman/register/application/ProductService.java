package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.Product;


public interface ProductService extends ModelService<Product> {

    Product register(Product product);
}
