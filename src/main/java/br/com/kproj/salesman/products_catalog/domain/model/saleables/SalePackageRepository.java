package br.com.kproj.salesman.products_catalog.domain.model.saleables;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Optional;

public interface SalePackageRepository extends BaseRepository<SalePackage, Long> {

    Optional<SalePackage> findBySaleable(SalePackage salePackage, SaleableUnit saleable);

}
