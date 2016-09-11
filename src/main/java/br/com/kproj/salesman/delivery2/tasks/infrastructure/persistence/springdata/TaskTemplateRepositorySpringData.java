package br.com.kproj.salesman.delivery2.tasks.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TaskTemplateRepositorySpringData extends BaseRepositoryLegacy<TaskTemplateEntity, Long> {

    @Query("SELECT tt FROM TaskTemplateEntity AS tt WHERE tt.saleable = :saleable AND tt.parentId is null AND tt.region = :region")
    List<TaskTemplateEntity> findTaskTemplateBy(@Param("saleable")SaleableUnitEntity saleable, @Param("region")OperationRegionEntity region);

}
