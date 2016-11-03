package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("regionTemplateRepository")
public interface RegionRepositorySpringData extends BaseRepositoryLegacy<OperationRegionEntity, Long> {



}
