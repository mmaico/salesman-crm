package br.com.kproj.salesman.administration.approval_negotiation.view.subscribers;


import br.com.kproj.salesman.administration.approval_negotiation.application.RequestApprovalFacade;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.PersonApproval;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.infrastructure.events.TimelineEvaluationMessage;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.EvaluationRequest.createEvaluation;
import static br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverBuilder.createApprover;

@Component
public class EvaluationApproverSubscriber {

    @Autowired
    private RequestApprovalFacade application;


    @Subscribe
    public void requestApprovalAvaluation(TimelineEvaluationMessage message) {
        Negotiation negotiation = new Negotiation(message.getNegotiationId());
        Approver approver = createApprover(message.getApproverId()).build();
        PersonApproval.Status status = PersonApproval.Status.get(message.getStatus());

        application.doEvaluation(createEvaluation(negotiation, approver, status));
    }

}
