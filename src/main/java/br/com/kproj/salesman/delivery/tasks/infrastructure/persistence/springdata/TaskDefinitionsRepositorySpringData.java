package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.TaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;


public interface TaskDefinitionsRepositorySpringData extends BaseRepositoryLegacy<TaskDefinitionEntity, Long> {

//    @Query("SELECT tt FROM TaskTemplateEntity AS tt WHERE tt.saleable = :saleable AND tt.parentId is null AND tt.region = :region")
//    List<TaskDefinitionEntity> findTaskTemplateBy(@Param("saleable")SaleableUnitEntity saleable, @Param("region")OperationRegionEntity region);

}
