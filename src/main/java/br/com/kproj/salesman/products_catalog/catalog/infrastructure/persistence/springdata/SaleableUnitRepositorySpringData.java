package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata.impl.SaleableUnitCustomRepository;

public interface SaleableUnitRepositorySpringData extends BaseRepositoryLegacy<SaleableUnitEntity, Long>, SaleableUnitCustomRepository {



}
