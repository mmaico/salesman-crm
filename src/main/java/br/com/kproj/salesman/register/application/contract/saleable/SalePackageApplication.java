package br.com.kproj.salesman.register.application.contract.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface SalePackageApplication extends ModelService<SalePackage> {

    SalePackage register(SalePackage salePackageItem);

    SalePackage addProductOrService(SalePackage salePackage, SaleableUnit saleable);

    SalePackage removeProductOrService(SalePackage salePackage, SaleableUnit saleable);


}
