package br.com.kproj.salesman.register.application.contract.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface SalePackageApplication extends ModelLegacyService<SalePackageEntity> {

    SalePackageEntity register(SalePackageEntity salePackageItem);

    SalePackageEntity addProductOrService(SalePackageEntity salePackage, SaleableUnitEntity saleable);

    SalePackageEntity removeProductOrService(SalePackageEntity salePackage, SaleableUnitEntity saleable);


}
