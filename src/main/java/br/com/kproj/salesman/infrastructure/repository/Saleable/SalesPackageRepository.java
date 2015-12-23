package br.com.kproj.salesman.infrastructure.repository.Saleable;


import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.entity.saleable.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface SalesPackageRepository extends BaseSaleableRepository<SalePackage> {

    @Query("SELECT p FROM SalePackage AS p JOIN p.saleableUnits AS s WHERE s = :saleable AND p = :packageValue")
    Optional<SalePackage> findBySaleable(@Param("packageValue") SalePackage salePackageValue, @Param("saleable") SaleableUnit saleable);

    @Query("SELECT p FROM SalePackage AS p WHERE p.type = 'PACKAGE' AND p.id = :id")
    Optional<SalePackage> getOne(@Param("id")Long id);

    @Query("SELECT s FROM SalePackage AS s WHERE s.type = 'PACKAGE' ORDER BY s.name")
    Page<SalePackage> findAll(Pageable pageable);

}
