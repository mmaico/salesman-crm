package br.com.kproj.salesman.products_catalog.infrastructure.persistence;


import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.Product;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.ProductRepository;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.ProductRepositorySpringData;
import com.trex.clone.BusinessModelClone;
import com.trex.proxy.BusinessModelProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryHibernate implements ProductRepository {

    @Autowired
    private ProductRepositorySpringData repository;


    @Override
    public Page<Product> findAll(Pageable page) {
        Page<ProductEntity> productsEntities = repository.findAll(page);

        Product proxy = BusinessModelProxy.from(productsEntities.getContent().get(0)).proxy(Product.class);

        List<Product> converteds = productsEntities.getContent().stream()
                .map(item -> BusinessModelClone.from(item).convertTo(Product.class))
                .collect(Collectors.toList());

        return new PageImpl<>(converteds);
    }

    @Override
    public Optional<Product> findOne(Long id) {
        ProductEntity result = repository.findOne(id);
        return null;
    }

    @Override
    public Optional<Product> save(Product entity) {

        return null;
    }
}
