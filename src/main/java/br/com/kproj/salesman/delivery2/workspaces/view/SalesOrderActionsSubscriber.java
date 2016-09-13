package br.com.kproj.salesman.delivery2.workspaces.view;


import br.com.kproj.salesman.infrastructure.events.messages.NewSalesOrderMessage;
import com.google.common.eventbus.Subscribe;

public class SalesOrderActionsSubscriber {

    @Subscribe
    public void createWorkspaceFor(NewSalesOrderMessage message) {
        
    }
}
