package br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;

import java.util.Optional;

public interface SalePackageRepository extends BaseRepository<SalePackage, Long> {

    void removeRelation(SaleableRelation relation);

    void addSaleable(SalePackage salePackage, SaleableUnit saleable);

}
