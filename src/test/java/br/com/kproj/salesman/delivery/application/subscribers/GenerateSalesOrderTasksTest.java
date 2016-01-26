package br.com.kproj.salesman.delivery.application.subscribers;

import br.com.kproj.salesman.delivery.application.tasks.TaskApplication;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.events.messages.NewSalesOrderMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class GenerateSalesOrderTasksTest {

    @InjectMocks
    private GenerateSalesOrderTasks generate;

    @Mock
    private TaskApplication application;

    @Test
    public void shouldCallMethodToGenerateTasks() throws Exception {
        SalesOrder salesOrderMock = Mockito.mock(SalesOrder.class);
        NewSalesOrderMessage message = NewSalesOrderMessage.create(salesOrderMock);


        generate.generateTaskBySalesOrder(message);

        verify(application, times(1)).generateTaskByNewSalesOrder(salesOrderMock);
    }

}