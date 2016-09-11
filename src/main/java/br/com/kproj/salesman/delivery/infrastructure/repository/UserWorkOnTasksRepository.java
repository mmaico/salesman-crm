package br.com.kproj.salesman.delivery.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserWorkOnTasksRepository extends BaseRepositoryLegacy<UserEntity, Long> {

    @Query("SELECT DISTINCT user FROM Task AS t JOIN t.signedBy AS user")
    List<UserEntity> findUsersWorksInTasks();

    @Query("SELECT count(*) FROM Task AS t JOIN t.signedBy AS user " +
            " WHERE user = :user AND t.status =:status")
    Long countOnAllTasksBy(@Param("user") UserEntity user, @Param("status") TaskStatus status);

    @Query("SELECT DISTINCT user FROM Task AS t JOIN t.signedBy AS user WHERE t.salesOrder =:salesOrder")
    List<UserEntity> findUsersWorksOnSalesOrder(@Param("salesOrder")SalesOrderEntity salesOrderEntity);

    @Query("SELECT count(*) FROM Task AS t JOIN t.signedBy AS user " +
            " WHERE user = :user AND t.status = :status AND t.salesOrder = :salesOrder")
    Long countOnTaskBy(@Param("user")UserEntity user, @Param("salesOrder") SalesOrderEntity salesOrderEntity, @Param("status") TaskStatus status);
}
