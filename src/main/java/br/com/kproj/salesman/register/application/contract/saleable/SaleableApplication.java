package br.com.kproj.salesman.register.application.contract.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface SaleableApplication extends ModelLegacyService<SaleableUnitEntity> {


    List<SaleableUnitEntity> getByType(SaleableTypeEntity saleableType);
}
