package br.com.kproj.salesman.delivery.application.subscribers;

import br.com.kproj.salesman.delivery.application.tasks.TaskApplication;
import br.com.kproj.salesman.infrastructure.events.messages.NewSalesOrderMessage;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateSalesOrderTasks {

    @Autowired
    private TaskApplication application;

    @Subscribe
    public void generateTaskBySalesOrder(NewSalesOrderMessage message) throws Exception {
        application.generateTaskByNewSalesOrder(message.getSalesOrderEntity());
    }
}
