package br.com.kproj.salesman.delivery.infrastructure.generatebysalesorder;

import br.com.kproj.salesman.delivery.infrastructure.generatebysalesorder.convert.TaskTemplateToTask;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderItemBuilder;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
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
public class SalesOrderEntityTaskEntityItemProcessorTest {

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
        SalesOrderEntity salesOrderEntity = getSalesOrderStub();
        List<TaskEntity> tasksMock = Lists.newArrayList(mock(TaskEntity.class), mock(TaskEntity.class), mock(TaskEntity.class));
        List<TaskTemplateEntity> tasksOne = getTasksOne();
        List<TaskTemplateEntity> tasksTwo = getTasksTwo();

        List<SalesOrderItem> salesOrderItems = salesOrderEntity.getSalesOrderItems();

        given(repository.findTaskTemplateBy(salesOrderItems.get(0).getSaleableUnit(), salesOrderEntity.getOperationRegionEntity()))
                    .willReturn(tasksOne);

        given(repository.findTaskTemplateBy(salesOrderItems.get(1).getSaleableUnit(), salesOrderEntity.getOperationRegionEntity()))
                .willReturn(tasksTwo);

        given(processor.getConverter(salesOrderEntity)).willReturn(taskTemplateToTask);

        given(taskTemplateToTask.convert(tasksOne.get(0))).willReturn(tasksMock.get(0));
        given(taskTemplateToTask.convert(tasksTwo.get(0))).willReturn(tasksMock.get(1));
        given(taskTemplateToTask.convert(tasksTwo.get(1))).willReturn(tasksMock.get(2));

        List<TaskEntity> result = processor.process(salesOrderEntity);

        assertThat(result.size(), Matchers.is(3));
        assertThat(result.contains(tasksMock.get(0)), Matchers.is(Boolean.TRUE));
        assertThat(result.contains(tasksMock.get(1)), Matchers.is(Boolean.TRUE));
        assertThat(result.contains(tasksMock.get(2)), Matchers.is(Boolean.TRUE));
    }

    private SalesOrderEntity getSalesOrderStub() {
        SalesOrderItem itemOne = SalesOrderItemBuilder.createSalesOrderItem(1l)
                .withSaleable(new ProductEntity(1l)).build();

        SalesOrderItem itemTwo = SalesOrderItemBuilder.createSalesOrderItem(1l)
                .withSaleable(new ProductEntity(2l)).build();

        List<SalesOrderItem> items = Lists.newArrayList(itemOne, itemTwo);
        return SalesOrderBuilder.createSalesOrder(10l)
                .withOperationRegion(new OperationRegionEntity(1l))
                .withSalesOrderItems(items).build();


    }

    private List<TaskTemplateEntity> getTasksTwo() {
        TaskTemplateEntity templateOne = new TaskTemplateEntity();
        templateOne.setId(1l);

        TaskTemplateEntity templateTwo = new TaskTemplateEntity();
        templateTwo.setId(2l);

        return Lists.newArrayList(templateOne, templateTwo);
    }

    private List<TaskTemplateEntity> getTasksOne() {
        TaskTemplateEntity templateOne = new TaskTemplateEntity();
        templateOne.setId(33l);

        return Lists.newArrayList(templateOne);
    }

}