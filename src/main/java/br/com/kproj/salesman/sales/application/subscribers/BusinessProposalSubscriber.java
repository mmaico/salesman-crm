package br.com.kproj.salesman.sales.application.subscribers;

import br.com.kproj.salesman.infrastructure.events.messages.BusinessProposalClosedWonMessage;
import br.com.kproj.salesman.sales.application.SalesOrderApplication;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessProposalSubscriber {

    @Autowired
    private SalesOrderApplication application;

    @Subscribe
    public void generateSalesOrder(BusinessProposalClosedWonMessage message) {

        application.register(message.getProposal());
    }
}
