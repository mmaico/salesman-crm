package br.com.kproj.salesman.infrastructure.repository.task;


import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends BaseRepository<Task, Long> {

    @Query("SELECT t FROM Task AS t WHERE t.salesOrder = :salesOrder ORDER BY t.deadline ASC")
    List<Task> findBySalesOrder(@Param("salesOrder") SalesOrder salesOrder);

    @Query("SELECT " +
            "   CASE WHEN count(*) > 0 " +
            "       THEN true " +
            "       ELSE false " +
            "   END  " +
            "FROM Task AS t JOIN t.tasksChilds AS taskChild " +
            "WHERE taskChild = :task")
    Boolean isSomeonesSon(@Param("task")Task task);
}
