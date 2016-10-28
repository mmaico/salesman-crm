package br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface SalePackageRepositorySpringData extends BaseRepositoryLegacy<SalePackageEntity, Long> {

    @Query("SELECT p FROM SalePackageEntity AS p JOIN p.saleableUnits AS s WHERE s = :saleable AND p = :packageValue")
    Optional<SalePackageEntity> findBySaleable(@Param("packageValue") SalePackageEntity salePackageValue, @Param("saleable") SaleableUnitEntity saleable);

    @Query("SELECT p FROM SalePackageEntity AS p WHERE p.id = :id")
    Optional<SalePackageEntity> getOne(@Param("id") Long id);

    @Query("SELECT s FROM SalePackageEntity AS s ORDER BY s.name")
    Page<SalePackageEntity> findAll(Pageable pageable);

}
