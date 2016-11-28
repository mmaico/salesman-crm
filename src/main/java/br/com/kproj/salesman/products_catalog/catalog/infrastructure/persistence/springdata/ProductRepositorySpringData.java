package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ProductRepositorySpringData extends BaseRepositoryLegacy<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity AS p WHERE p.id = :id")
    Optional<ProductEntity> getOne(@Param("id") Long id);

    @Query("SELECT p FROM ProductEntity AS p ORDER BY p.saleable.name")
    Page<ProductEntity> findAll(Pageable pageable);
}
