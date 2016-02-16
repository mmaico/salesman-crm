package br.com.kproj.salesman.delivery.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserWorkOnTasksRepository extends BaseRepository<User, Long> {

    @Query("SELECT DISTINCT user FROM Task AS t JOIN t.signedBy AS user")
    List<User> findUsersWorksInTasks();

    @Query("SELECT count(*) FROM Task AS t JOIN t.signedBy AS user " +
            " WHERE user = :user AND t.status =:status")
    Long countOnAllTasksBy(@Param("user") User user, @Param("status") TaskStatus status);

    @Query("SELECT DISTINCT user FROM Task AS t JOIN t.signedBy AS user WHERE t.salesOrder =:salesOrder")
    List<User> findUsersWorksOnSalesOrder(@Param("salesOrder")SalesOrder salesOrder);

    @Query("SELECT count(*) FROM Task AS t JOIN t.signedBy AS user " +
            " WHERE user = :user AND t.status = :status AND t.salesOrder = :salesOrder")
    Long countOnTaskBy(@Param("user")User user, @Param("salesOrder") SalesOrder salesOrder, @Param("status") TaskStatus status);
}
