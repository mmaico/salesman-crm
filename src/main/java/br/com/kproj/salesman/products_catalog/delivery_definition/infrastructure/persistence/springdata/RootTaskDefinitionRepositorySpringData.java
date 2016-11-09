package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.RootTaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("rootTaskDefinitionRepository")
public interface RootTaskDefinitionRepositorySpringData extends BaseRepositoryLegacy<RootTaskDefinitionEntity, Long> {

    @Query("SELECT tt FROM RootTaskDefinitionEntity AS tt WHERE tt.saleable = :saleable AND tt.region = :region")
    List<RootTaskDefinitionEntity> findRootTask(@Param("saleable") SaleableUnitEntity saleable, @Param("region") OperationRegionEntity region);

    @Query("SELECT tt FROM RootTaskDefinitionEntity AS tt WHERE tt.saleable = :saleable")
    List<RootTaskDefinitionEntity> findRootTask(@Param("saleable") SaleableUnitEntity saleable);

}
