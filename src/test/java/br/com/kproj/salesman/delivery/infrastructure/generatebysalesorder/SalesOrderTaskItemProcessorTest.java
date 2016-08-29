package br.com.kproj.salesman.delivery.infrastructure.generatebysalesorder;

import br.com.kproj.salesman.delivery.infrastructure.generatebysalesorder.convert.TaskTemplateToTask;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderItemBuilder;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.repository.task.TaskTemplateRepository;
import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class SalesOrderTaskItemProcessorTest {

    @InjectMocks
    private SalesOrderTaskItemProcessor processor;

    @Mock
    private TaskTemplateRepository repository;

    @Mock
    private TaskTemplateToTask taskTemplateToTask;

    @Before
    public void setUp() {
        this.processor = Mockito.spy(this.processor);
    }


    @Test
    public void shouldFindAllTasksBySalesOrder() throws Exception {
        SalesOrder salesOrder = getSalesOrderStub();
        List<Task> tasksMock = Lists.newArrayList(mock(Task.class), mock(Task.class), mock(Task.class));
        List<TaskTemplate> tasksOne = getTasksOne();
        List<TaskTemplate> tasksTwo = getTasksTwo();

        List<SalesOrderItem> salesOrderItems = salesOrder.getSalesOrderItems();

        given(repository.findTaskTemplateBy(salesOrderItems.get(0).getSaleableUnit(), salesOrder.getOperationRegionEntity()))
                    .willReturn(tasksOne);

        given(repository.findTaskTemplateBy(salesOrderItems.get(1).getSaleableUnit(), salesOrder.getOperationRegionEntity()))
                .willReturn(tasksTwo);

        given(processor.getConverter(salesOrder)).willReturn(taskTemplateToTask);

        given(taskTemplateToTask.convert(tasksOne.get(0))).willReturn(tasksMock.get(0));
        given(taskTemplateToTask.convert(tasksTwo.get(0))).willReturn(tasksMock.get(1));
        given(taskTemplateToTask.convert(tasksTwo.get(1))).willReturn(tasksMock.get(2));

        List<Task> result = processor.process(salesOrder);

        assertThat(result.size(), Matchers.is(3));
        assertThat(result.contains(tasksMock.get(0)), Matchers.is(Boolean.TRUE));
        assertThat(result.contains(tasksMock.get(1)), Matchers.is(Boolean.TRUE));
        assertThat(result.contains(tasksMock.get(2)), Matchers.is(Boolean.TRUE));
    }

    private SalesOrder getSalesOrderStub() {
        SalesOrderItem itemOne = SalesOrderItemBuilder.createSalesOrderItem(1l)
                .withSaleable(new ProductEntity(1l)).build();

        SalesOrderItem itemTwo = SalesOrderItemBuilder.createSalesOrderItem(1l)
                .withSaleable(new ProductEntity(2l)).build();

        List<SalesOrderItem> items = Lists.newArrayList(itemOne, itemTwo);
        return SalesOrderBuilder.createSalesOrder(10l)
                .withOperationRegion(new OperationRegionEntity(1l))
                .withSalesOrderItems(items).build();


    }

    private List<TaskTemplate> getTasksTwo() {
        TaskTemplate templateOne = new TaskTemplate();
        templateOne.setId(1l);

        TaskTemplate templateTwo = new TaskTemplate();
        templateTwo.setId(2l);

        return Lists.newArrayList(templateOne, templateTwo);
    }

    private List<TaskTemplate> getTasksOne() {
        TaskTemplate templateOne = new TaskTemplate();
        templateOne.setId(33l);

        return Lists.newArrayList(templateOne);
    }

}