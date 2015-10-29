package br.com.kproj.salesman.infrastructure.repository.Saleable;


import br.com.kproj.salesman.infrastructure.entity.saleable.Package;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PackageRepository extends BaseSaleableRepository<Package> {

    @Query("SELECT p FROM Package AS p JOIN p.saleableUnits AS s WHERE s = :saleable AND p = :packageValue")
    Optional<Package> findBySaleable(@Param("packageValue") Package packageValue, @Param("saleable") SaleableUnit saleable);

    @Query("SELECT p FROM Package AS p WHERE p.type = 'PACKAGE' AND p.id = :id")
    Optional<Package> getOne(@Param("id")Long id);

}
