package br.com.kproj.salesman.delivery.workspaces.view;



import br.com.kproj.salesman.delivery.workspaces.application.WorkspaceFacade;
import br.com.kproj.salesman.delivery.workspaces.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.infrastructure.events.NewSalesOrderMessage;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesOrderActionsSubscriber {

    @Autowired
    private WorkspaceFacade workspaceFacade;

    @Subscribe
    public void createWorkspaceFor(NewSalesOrderMessage message) {
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setId(message.getSalesOrderId());

        workspaceFacade.createFor(salesOrder);
    }
}
