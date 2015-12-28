package br.com.kproj.salesman.infrastructure.repository.Saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableType;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SaleableUnitRepository extends BaseSaleableRepository<SaleableUnit> {

    @Query("SELECT s FROM SaleableUnit AS s WHERE s.id = :id")
    Optional<SaleableUnit> getOne(@Param("id")Long id);

    @Query("SELECT s FROM SaleableUnit AS s WHERE s.type = :saleableType")
    List<SaleableUnit> getByType(@Param("saleableType") SaleableType saleableType);
}