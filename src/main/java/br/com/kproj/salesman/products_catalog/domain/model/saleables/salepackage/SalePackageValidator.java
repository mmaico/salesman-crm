package br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage;


import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;

public interface SalePackageValidator {

    void checkRules(SaleableUnit saleableUnit);
}
