package br.com.kproj.salesman.register.application.contract.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface SalePackageService extends ModelService<SalePackage> {

    SalePackage register(SalePackage salePackageItem);

}
