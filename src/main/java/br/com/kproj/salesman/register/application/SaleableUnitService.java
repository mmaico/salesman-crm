package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.service.ModelService;


public interface SaleableUnitService extends ModelService<SaleableUnit> {

    SaleableUnit register(SaleableUnit saleableUnit);
}
