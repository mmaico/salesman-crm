package br.com.kproj.salesman.negotiationold.proposal.approval.application.subscribers;


import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import br.com.kproj.salesman.infrastructure.events.messages.ProposalAuditingAfterUpdateMessage;
import br.com.kproj.salesman.negotiationold.proposal.approval.application.RequestApprovalApplication;
import br.com.kproj.salesman.negotiationold.proposal.approval.predicates.StartApprovalProcessPredicate;
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
            RequestApprovalEntity requestApprovalEntity = createRequestApproval()
                    .withProposal(createBusinessProposal(message.getBefore().getEntityId()).build())
                    .withUserRequester(message.getUserThatChanged())
                    .withStatus(RequestApprovalEntity.RequestApprovalStatus.WAITING).build();

            application.register(requestApprovalEntity);
        }

    }

}
