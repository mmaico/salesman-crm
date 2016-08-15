package br.com.kproj.salesman.infrastructure.repository.Saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SaleableUnitRepository extends BaseSaleableRepository<SaleableUnitEntity> {

    @Query("SELECT s FROM SaleableUnitEntity AS s WHERE s.id = :id")
    Optional<SaleableUnitEntity> getOne(@Param("id")Long id);

    @Query("SELECT s FROM SaleableUnitEntity AS s WHERE s.type = :saleableType")
    List<SaleableUnitEntity> getByType(@Param("saleableType") SaleableTypeEntity saleableType);
}
