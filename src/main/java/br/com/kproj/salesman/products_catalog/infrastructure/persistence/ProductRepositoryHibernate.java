package br.com.kproj.salesman.products_catalog.infrastructure.persistence;


import br.com.kproj.salesman.delivery.tasks_template.infrastructure.persistence.springdata.SaleableRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.saleable.MeasurementUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.products.Product;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.products.ProductRepository;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.ProductRepositorySpringData;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.ProductEntityToProductConverter;
import com.trex.clone.BusinessModelClone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.trex.clone.BusinessModelClone.*;
import static com.trex.clone.BusinessModelClone.from;

@Repository
public class ProductRepositoryHibernate extends BaseRespositoryImpl<Product, ProductEntity> implements ProductRepository {

    private ProductRepositorySpringData repository;

    private SaleableRepositorySpringData saleableRepository;

    private ProductEntityToProductConverter converter;

    @Autowired
    public ProductRepositoryHibernate(ProductRepositorySpringData repository, ProductEntityToProductConverter converter,
                                      SaleableRepositorySpringData saleableRepository) {
        this.repository = repository;
        this.converter = converter;
        this.saleableRepository = saleableRepository;
    }

    public Optional<Product> save(Product product) {

        SaleableUnitEntity saleableUnitEntity = saleableRepository.findOne(product.getId());
        from(saleableUnitEntity).merge(product);

        ProductEntity productEntity = from(product).convertTo(ProductEntity.class);

        ProductEntity entity = repository.save(productEntity);
        return Optional.ofNullable(getConverter().convert(entity));

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
