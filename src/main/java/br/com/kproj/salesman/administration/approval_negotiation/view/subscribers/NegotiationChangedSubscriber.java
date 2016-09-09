package br.com.kproj.salesman.administration.approval_negotiation.view.subscribers;


import br.com.kproj.salesman.administration.approval_negotiation.application.RequestApprovalFacade;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApproval;
import br.com.kproj.salesman.infrastructure.events.NegotiationChangedMessage;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalBuilder.createRequestApproval;

@Component
public class NegotiationChangedSubscriber {


    @Autowired
    private RequestApprovalFacade application;


    @Subscribe
    public void startApprovalProcessIfNeeded(NegotiationChangedMessage message) {

        RequestApproval requestApproval = createRequestApproval()
                .withRequester(message.getUserWhoChangedId())
                .withNegotiationId(message.getNegotiationId()).build();

        application.register(requestApproval);

    }

}
