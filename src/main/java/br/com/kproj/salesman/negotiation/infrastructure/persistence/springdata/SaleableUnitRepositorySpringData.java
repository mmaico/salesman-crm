package br.com.kproj.salesman.negotiation.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("saleableUnitRepositoryNegotiation")
public interface SaleableUnitRepositorySpringData extends BaseRepositoryLegacy<SaleableUnitEntity, Long> {


}
