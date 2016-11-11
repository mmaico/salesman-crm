package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.RootTaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.SubtaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("subtaskDefinitionRepository")
public interface SubtaskDefinitionRepositorySpringData extends BaseRepositoryLegacy<SubtaskDefinitionEntity, Long> {

    @Query("SELECT tt FROM SubtaskDefinitionEntity AS tt WHERE tt.parent = :rootTask")
    List<SubtaskDefinitionEntity> findAll(@Param("rootTask") RootTaskDefinitionEntity rootTask);

}
