package br.com.kproj.salesman.delivery.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskChangeHistory;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface TaskChangeHistoryRepository extends BaseRepositoryLegacy<TaskChangeHistory, Long> {


    @Query("SELECT tch.dateOfChange FROM TaskChangeHistory AS tch JOIN tch.taskChanged AS task " +
            " WHERE task.salesOrder = :salesOrder  ORDER BY tch.dateOfChange DESC ")
    Page<Date> findDateFromOldestHistory(@Param("salesOrder") SalesOrderEntity salesOrderEntity, Pageable pageable);

    @Query("SELECT tch.dateOfChange FROM TaskChangeHistory AS tch JOIN tch.taskChanged AS task " +
            " WHERE task.salesOrder = :salesOrder ORDER BY tch.dateOfChange ASC ")
    Page<Date> findDateFromNewestHistory(@Param("salesOrder") SalesOrderEntity salesOrderEntity, Pageable pageable);

    @Query("SELECT COUNT(*) FROM TaskChangeHistory AS tch " +
            " WHERE tch.dateOfChange >= :startDate AND tch.dateOfChange <= :endDate " +
            " AND tch.statusChanged = :status")
    Long countHistoryByRangeDatesAndStatus(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("status")TaskStatusEntity status);
}
