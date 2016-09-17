package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TaskRepositorySpringData extends BaseRepositoryLegacy<TaskEntity, Long> {

    @Query("SELECT t FROM TaskEntity AS t WHERE t.salesOrder = :salesOrder AND t.parentId is null ORDER BY t.deadline ASC")
    List<TaskEntity> findBySalesOrder(@Param("salesOrder") SalesOrderEntity salesOrderEntity);

}
