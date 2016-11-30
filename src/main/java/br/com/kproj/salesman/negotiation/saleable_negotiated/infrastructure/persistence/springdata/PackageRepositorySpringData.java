package br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("packageRepositoryNegotiatedSaleableModule")
public interface PackageRepositorySpringData extends BaseRepositoryLegacy<SalePackageEntity, Long> {


}
