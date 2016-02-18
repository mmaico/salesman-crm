package br.com.kproj.salesman.infrastructure.repository.task;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
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
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setId(1l);

        List<Task> result = repository.findBySalesOrder(salesOrder);

        assertThat(result.size(), is(5));
    }

    @Test
    public void shouldReturnAllTasksOrderedByDeadline() {
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setId(1l);

        List<Task> result = repository.findBySalesOrder(salesOrder);


        assertThat(result.get(0).getId(), is(3l));
        assertThat(result.get(1).getId(), is(2l));
        assertThat(result.get(2).getId(), is(1l));
    }


    @Test
    public void shouldReturnTrueWhenHasParent() {
        Task task = new Task();
        task.setId(4l);

        Boolean result = repository.isSomeonesSon(task);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseWhenNotHaveParent() {
        Task task = new Task();
        task.setId(1l);

        Boolean result = repository.isSomeonesSon(task);

        assertThat(result, is(Boolean.FALSE));
    }


}