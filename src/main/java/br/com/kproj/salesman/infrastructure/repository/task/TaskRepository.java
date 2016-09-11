package br.com.kproj.salesman.infrastructure.repository.task;


import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends BaseRepositoryLegacy<TaskEntity, Long> {

    @Query("SELECT t FROM Task AS t WHERE t.salesOrder = :salesOrder AND t.parentId is null ORDER BY t.deadline ASC")
    List<TaskEntity> findBySalesOrder(@Param("salesOrder") SalesOrderEntity salesOrderEntity);

    @Query("SELECT " +
            "   CASE WHEN count(*) > 0 " +
            "       THEN true " +
            "       ELSE false " +
            "   END  " +
            "FROM Task AS t JOIN t.tasksChilds AS taskChild " +
            "WHERE taskChild = :task")
    Boolean isSomeonesSon(@Param("task")TaskEntity taskEntity);

    @Query("SELECT t FROM Task AS t WHERE t.salesOrder = :salesOrder AND t " +
            " NOT IN (SELECT child FROM Task AS ta JOIN ta.tasksChilds AS child " +
            "   WHERE ta.salesOrder = :salesOrder)")
    List<TaskEntity> findTaskRootBy(@Param("salesOrder")SalesOrderEntity salesOrderEntity);


    @Query("SELECT COUNT(t) FROM Task AS t WHERE t.status = :status")
    Long countByStatus(@Param("status")TaskStatus status);

    @Query("SELECT COUNT(t) FROM Task AS t WHERE t.status = :status AND t.salesOrder = :salesOrder")
    Long countByStatus(@Param("status")TaskStatus status, @Param("salesOrder") SalesOrderEntity salesOrderEntity);

    @Query("SELECT COUNT(t) FROM Task AS t WHERE t.salesOrder = :salesOrder")
    Long countBySalesOrder(@Param("salesOrder")SalesOrderEntity salesOrderEntity);

    @Query("SELECT t FROM Task AS t WHERE t.id = :id")
    Optional<TaskEntity> getOne(@Param("id")Long id);

}
