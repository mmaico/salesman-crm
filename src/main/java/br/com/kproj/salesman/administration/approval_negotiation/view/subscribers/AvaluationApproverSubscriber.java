package br.com.kproj.salesman.administration.approval_negotiation.view.subscribers;


import br.com.kproj.salesman.administration.approval_negotiation.application.RequestApprovalFacade;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.BusinessProposalApprovalActivity;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import br.com.kproj.salesman.infrastructure.events.messages.TimelineSaveMessage;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvaluationApproverSubscriber {

    @Autowired
    private RequestApprovalFacade application;

    @Autowired
    private SecurityHelper security;



    @Subscribe
    public void requestApprovalAvaluation(TimelineSaveMessage timelineSaveMessage) {

        TimelineActivity activity = timelineSaveMessage.getActivity();

        if (!(activity instanceof BusinessProposalApprovalActivity)) return;

        BusinessProposalApprovalActivity approvalActivity = (BusinessProposalApprovalActivity) activity;

//        application.evaluationApprover(timelineSaveMessage.getBusinessProposalEntity(),
//                security.getPrincipal().getUser(), approvalActivity.getAvaluation());
    }

}
