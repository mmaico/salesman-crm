package br.com.kproj.salesman.delivery.delivery.view;



import br.com.kproj.salesman.delivery.delivery.application.DeliveryFacade;
import br.com.kproj.salesman.delivery.delivery.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.infrastructure.events.NewSalesOrderMessage;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesOrderActionsSubscriber {

    @Autowired
    private DeliveryFacade workspaceFacade;

    @Subscribe
    public void createWorkspaceFor(NewSalesOrderMessage message) {
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setId(message.getSalesOrderId());

        workspaceFacade.createFor(salesOrder);
    }
}
