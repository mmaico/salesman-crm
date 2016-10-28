package br.com.kproj.salesman.products_catalog.domain.model.saleables;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SaleableUnitRepository {

    Page<SaleableUnit> findAll(Pageable page);

    Optional<SaleableUnit> findOne(Long id);

    Optional<SaleableUnit> save(SaleableUnit entity);

}
