package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.TaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TaskDefinitionsRepositorySpringData extends BaseRepositoryLegacy<TaskDefinitionEntity, Long> {

//    @Query("SELECT tt FROM TaskTemplateEntity AS tt WHERE tt.saleable = :saleable AND tt.parentId is null AND tt.region = :region")
//    List<TaskDefinitionEntity> findTaskTemplateBy(@Param("saleable")SaleableUnitEntity saleable, @Param("region")OperationRegionEntity region);

}
