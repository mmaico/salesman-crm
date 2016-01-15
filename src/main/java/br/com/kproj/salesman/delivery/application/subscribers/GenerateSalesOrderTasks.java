package br.com.kproj.salesman.delivery.application.subscribers;

import br.com.kproj.salesman.infrastructure.events.messages.NewSalesOrderMessage;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

@Component
public class GenerateSalesOrderTasks {


    @Subscribe
    public void generateTaskBySalesOrder(NewSalesOrderMessage message) {

    }
}
