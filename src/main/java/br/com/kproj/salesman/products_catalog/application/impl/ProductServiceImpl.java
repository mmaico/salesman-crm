package br.com.kproj.salesman.products_catalog.application.impl;

import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.products_catalog.application.ProductFacade;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.Product;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.ProductRepository;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl extends BaseModelServiceImpl<Product> implements ProductFacade {

    @Autowired
    private ProductRepository repository;

    @Autowired
    @Qualifier("saleableDomainValidator")
    private SaleableValidator validator;


    @Override
    public Optional<Product> register(Product product) {

        validator.checkRules(product);

        return repository.save(product);
    }

    @Override
    public BaseRepository<Product, Long> getRepository() {
        return repository;
    }
}
