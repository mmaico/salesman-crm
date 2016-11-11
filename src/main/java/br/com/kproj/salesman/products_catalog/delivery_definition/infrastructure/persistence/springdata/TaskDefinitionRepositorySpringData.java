package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.TaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskDefinitionRepository")
public interface TaskDefinitionRepositorySpringData extends BaseRepositoryLegacy<TaskDefinitionEntity, Long> {

    @Query("SELECT tt FROM TaskDefinitionEntity AS tt WHERE tt.saleable = :saleable")
    List<TaskDefinitionEntity> findAll(@Param("saleable") SaleableUnitEntity saleable);

}
