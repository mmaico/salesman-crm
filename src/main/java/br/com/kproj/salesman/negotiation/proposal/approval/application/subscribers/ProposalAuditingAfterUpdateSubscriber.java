package br.com.kproj.salesman.negotiation.proposal.approval.application.subscribers;


import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.events.messages.ProposalAuditingAfterUpdateMessage;
import br.com.kproj.salesman.negotiation.proposal.approval.application.RequestApprovalApplication;
import br.com.kproj.salesman.negotiation.proposal.approval.predicates.StartApprovalProcessPredicate;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder.createBusinessProposal;
import static br.com.kproj.salesman.infrastructure.entity.builders.RequestApprovalBuilder.createRequestApproval;

@Component
public class ProposalAuditingAfterUpdateSubscriber {

    @Autowired
    private StartApprovalProcessPredicate predicate;

    @Autowired
    private RequestApprovalApplication application;


    @Subscribe
    public void startApprovalProcessIfNeeded(ProposalAuditingAfterUpdateMessage message) {

        if (predicate.test(message)) {
            RequestApproval requestApproval = createRequestApproval()
                    .withProposal(createBusinessProposal(message.getBefore().getEntityId()).build())
                    .withUserRequester(message.getUserThatChanged())
                    .withStatus(RequestApproval.RequestApprovalStatus.WAITING).build();

            application.register(requestApproval);
        }

    }

}
