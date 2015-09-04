package br.com.kproj.salesman.register.domain;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;

public interface SaleableUnitDomainService {

	void verifyPreconditionToSave(SaleableUnit saleableUnit);
}
