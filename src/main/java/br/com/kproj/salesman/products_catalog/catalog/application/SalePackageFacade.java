package br.com.kproj.salesman.products_catalog.catalog.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackage;

public interface SalePackageFacade extends ModelFacade<SalePackage> {

    void addSaleable(SalePackage salePackage, SaleableUnit saleable);

    void removeSaleable(SalePackage salePackage, SaleableUnit saleable);
}
