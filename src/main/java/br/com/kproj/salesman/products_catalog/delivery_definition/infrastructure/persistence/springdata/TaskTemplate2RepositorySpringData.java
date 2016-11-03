package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskTemplate2Repository")
public interface TaskTemplate2RepositorySpringData extends BaseRepositoryLegacy<TaskTemplateEntity, Long> {

    @Query("SELECT tt FROM TaskTemplateEntity AS tt WHERE tt.saleable = :saleable AND tt.region = :region")
    List<TaskTemplateEntity> findTaskTemplateBy(@Param("saleable")SaleableUnitEntity saleable, @Param("region")OperationRegionEntity region);

    @Query("SELECT tt FROM TaskTemplateEntity AS tt WHERE tt.saleable = :saleable")
    List<TaskTemplateEntity> findTaskTemplateBy(@Param("saleable")SaleableUnitEntity saleable);

}
