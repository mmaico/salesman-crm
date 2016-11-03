package br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage;


import br.com.kproj.salesman.products_catalog.catalog.application.validators.IgnoreRules;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;

public interface SalePackageValidator {

    void checkRules(SaleableUnit saleableUnit, IgnoreRules rules);
    void checkRules(SaleableUnit saleableUnit);
}
