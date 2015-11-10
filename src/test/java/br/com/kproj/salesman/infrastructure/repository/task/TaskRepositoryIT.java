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

        assertThat(result.size(), is(4));
    }


}