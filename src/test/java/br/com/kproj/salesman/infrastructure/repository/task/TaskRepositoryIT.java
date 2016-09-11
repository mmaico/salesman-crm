package br.com.kproj.salesman.infrastructure.repository.task;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TaskRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private TaskRepository repository;

    @Test
    public void shouldReturnAllTasksAssociatedToSalesOrder() {
        SalesOrderEntity salesOrderEntity = new SalesOrderEntity();
        salesOrderEntity.setId(1l);

        List<TaskEntity> result = repository.findBySalesOrder(salesOrderEntity);

        assertThat(result.size(), is(5));
    }

    @Test
    public void shouldReturnAllTasksOrderedByDeadline() {
        SalesOrderEntity salesOrderEntity = new SalesOrderEntity();
        salesOrderEntity.setId(1l);

        List<TaskEntity> result = repository.findBySalesOrder(salesOrderEntity);


        assertThat(result.get(0).getId(), is(3l));
        assertThat(result.get(1).getId(), is(2l));
        assertThat(result.get(2).getId(), is(1l));
    }


    @Test
    public void shouldReturnTrueWhenHasParent() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(4l);

        Boolean result = repository.isSomeonesSon(taskEntity);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseWhenNotHaveParent() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(1l);

        Boolean result = repository.isSomeonesSon(taskEntity);

        assertThat(result, is(Boolean.FALSE));
    }


}