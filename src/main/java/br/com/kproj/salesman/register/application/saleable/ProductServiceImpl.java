package br.com.kproj.salesman.register.application.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.Product;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.Saleable.ProductRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.saleable.ProductService;
import br.com.kproj.salesman.register.domain.contract.SaleableUnitDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl extends BaseModelServiceImpl<Product> implements ProductService {

    private SaleableUnitDomainService domainService;

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, SaleableUnitDomainService domainService) {
        this.productRepository = productRepository;
        this.domainService = domainService;
    }

    public Product register(Product product) {
        domainService.verifyPreconditionToSave(product);
        return super.save(product);
    }

    @Override
    public Optional<Product> getOne(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public BaseRepository<Product, Long> getRepository() {
        return productRepository;
    }

}
