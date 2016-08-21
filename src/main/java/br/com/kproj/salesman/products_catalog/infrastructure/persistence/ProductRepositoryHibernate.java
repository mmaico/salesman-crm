package br.com.kproj.salesman.products_catalog.infrastructure.persistence;


import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.Product;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.ProductRepository;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.ProductRepositorySpringData;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.ProductEntityToProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryHibernate extends BaseRespositoryImpl<Product, ProductEntity> implements ProductRepository {

    @Autowired
    private ProductRepositorySpringData repository;

    @Autowired
    private ProductEntityToProductConverter converter;


    @Override
    public BaseRepositoryLegacy<ProductEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ProductEntity, Product> getConverter() {
        return converter;
    }

}
