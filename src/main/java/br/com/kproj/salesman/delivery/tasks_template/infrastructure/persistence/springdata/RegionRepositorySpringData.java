package br.com.kproj.salesman.delivery.tasks_template.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("regionTemplateRepository")
public interface RegionRepositorySpringData extends BaseRepositoryLegacy<OperationRegionEntity, Long> {



}
