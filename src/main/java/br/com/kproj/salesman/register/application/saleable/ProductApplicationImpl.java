package br.com.kproj.salesman.register.application.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.Saleable.ProductRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import br.com.kproj.salesman.register.application.contract.saleable.ProductApplication;
import br.com.kproj.salesman.register.domain.contract.SaleableUnitDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductApplicationImpl extends BaseModelServiceLegacyImpl<ProductEntity> implements ProductApplication {

    private SaleableUnitDomainService domainService;

    private ProductRepository productRepository;

    @Autowired
    public ProductApplicationImpl(ProductRepository productRepository, SaleableUnitDomainService domainService) {
        this.productRepository = productRepository;
        this.domainService = domainService;
    }

    public ProductEntity register(ProductEntity product) {

        return super.save(product, domainService);
    }

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Optional<ProductEntity> getOne(Long id) {
        return productRepository.getOne(id);
    }

    public BaseRepositoryLegacy<ProductEntity, Long> getRepository() {
        return productRepository;
    }

}
