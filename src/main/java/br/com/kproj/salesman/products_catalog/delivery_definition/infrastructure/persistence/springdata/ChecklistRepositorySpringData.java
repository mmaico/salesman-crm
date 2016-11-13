package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.ChecklistDefinitionEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.TaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("checklistDefinitionRepositoryTaskTemplateModule")
public interface ChecklistRepositorySpringData extends BaseRepositoryLegacy<ChecklistDefinitionEntity, Long> {

    @Query("SELECT c FROM ChecklistDefinitionEntity AS c WHERE c.taskDefinition = :task")
    List<ChecklistDefinitionEntity> findCheckListBy(@Param("task") TaskDefinitionEntity taskEntity);

}
