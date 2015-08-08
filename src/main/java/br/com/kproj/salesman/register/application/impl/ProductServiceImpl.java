package br.com.kproj.salesman.register.application.impl;

import br.com.kproj.salesman.infrastructure.entity.Product;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.ProductRepository;
import br.com.kproj.salesman.infrastructure.service.impl.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.ProductService;
import br.com.kproj.salesman.register.domain.ProductDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseModelServiceImpl<Product> implements ProductService {

    @Autowired
    private ProductDomainService domainService;

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public BaseRepository<Product, Long> getRepository() {
        return productRepository;
    }

    @Override
    public Product register(Product product) {
        domainService.verifyPreconditionToSave(product);
        return super.save(product);
    }

}
