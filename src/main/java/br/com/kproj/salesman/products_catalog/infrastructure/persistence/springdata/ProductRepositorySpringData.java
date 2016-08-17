package br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.repository.Saleable.BaseSaleableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ProductRepositorySpringData extends BaseSaleableRepository<ProductEntity> {

    @Query("SELECT p FROM ProductEntity AS p WHERE p.type = 'PRODUCT' AND p.id = :id")
    Optional<ProductEntity> getOne(@Param("id") Long id);

    @Query("SELECT p FROM ProductEntity AS p WHERE p.type = 'PRODUCT' ORDER BY p.name")
    Page<ProductEntity> findAll(Pageable pageable);
}
