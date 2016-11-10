package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.TaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("taskDefinitionRepository")
public interface TaskDefinitionRepositorySpringData extends BaseRepositoryLegacy<TaskDefinitionEntity, Long> {


}
