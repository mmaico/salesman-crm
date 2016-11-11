package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.RootTaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("rootTaskDefinitionRepository")
public interface RootTaskDefinitionRepositorySpringData extends BaseRepositoryLegacy<RootTaskDefinitionEntity, Long> {



}
