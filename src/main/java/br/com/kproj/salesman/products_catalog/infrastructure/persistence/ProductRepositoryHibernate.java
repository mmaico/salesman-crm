package br.com.kproj.salesman.products_catalog.infrastructure.persistence;


import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.products.Product;
import br.com.kproj.salesman.products_catalog.domain.model.products.ProductRepository;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.ProductRepositorySpringData;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.ProductEntityToProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.trex.clone.BusinessModelClone.from;

@Repository
public class ProductRepositoryHibernate extends BaseRespositoryImpl<Product, ProductEntity> implements ProductRepository {

    private ProductRepositorySpringData repository;

    private ProductEntityToProductConverter converter;

    @Autowired
    public ProductRepositoryHibernate(ProductRepositorySpringData repository, ProductEntityToProductConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Optional<Product> save(Product product) {

        Optional<ProductEntity> productEntity = repository.getOne(product.getId());
        if (productEntity.isPresent()) {
            from(product).merge(productEntity.get());
            return Optional.of(getConverter().convert(productEntity.get()));
        } else {
            ProductEntity newProduct = from(product).convertTo(ProductEntity.class);
            ProductEntity entity = repository.save(newProduct);
            //marcar um saleable com um tipo PRODUCT
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
