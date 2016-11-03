package br.com.kproj.salesman.products_catalog.catalog.application.impl;

import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.products_catalog.catalog.application.ProductFacade;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.products.Product;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.products.ProductRepository;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.products.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl extends BaseModelServiceImpl<Product> implements ProductFacade {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductValidator rules;


    @Override
    public Optional<Product> register(Product product) {
        rules.checkRules(product);

        return repository.save(product);
    }

    @Override
    public BaseRepository<Product, Long> getRepository() {
        return repository;
    }
}
