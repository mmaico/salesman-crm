package br.com.kproj.salesman.products_catalog.domain.model.salepackage;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;

import java.util.Optional;

public interface SalePackageRepository extends BaseRepository<SalePackage, Long> {

    Optional<SalePackage> findBySaleable(SalePackage salePackage, SaleableUnit saleable);

    void removeSaleable(SalePackage salePackage, SaleableUnit saleable);

    void addSaleable(SalePackage salePackage, SaleableUnit saleable);

}
