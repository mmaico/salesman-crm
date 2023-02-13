package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence;


import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.products.Product;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.products.ProductRepository;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.Represent;
import br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata.ProductRepositorySpringData;
import br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata.SaleableUnitRepositorySpringData;
import br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.translate.ProductEntityToProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.github.mmaico.clone.BusinessModelClone.from;


@Repository
public class ProductRepositoryHibernate extends BaseRespositoryImpl<Product, ProductEntity> implements ProductRepository {

    private ProductRepositorySpringData repository;

    private ProductEntityToProductConverter converter;

    private SaleableUnitRepositorySpringData saleableRepository;

    @Autowired
    public ProductRepositoryHibernate(ProductRepositorySpringData repository, ProductEntityToProductConverter converter,
                                      SaleableUnitRepositorySpringData saleableRepository) {
        this.repository = repository;
        this.converter = converter;
        this.saleableRepository = saleableRepository;
    }

    public Optional<Product> save(Product product) {

        Optional<ProductEntity> productEntity = repository.getOne(product.getId());
        if (productEntity.isPresent()) {
            from(product).merge(productEntity.get());
            return Optional.of(getConverter().convert(productEntity.get()));
        } else {
            ProductEntity newProduct = from(product).convertTo(ProductEntity.class);
            newProduct.setSaleable(new SaleableUnitEntity(product.getId()));
            ProductEntity entity = repository.save(newProduct);
            saleableRepository.represent(entity.getId(), Represent.PRODUCT);

            return Optional.ofNullable(getConverter().convert(entity));
        }
    }

    @Override
    public BaseRepositoryLegacy<ProductEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ProductEntity, Product> getConverter() {
        return converter;
    }

}
