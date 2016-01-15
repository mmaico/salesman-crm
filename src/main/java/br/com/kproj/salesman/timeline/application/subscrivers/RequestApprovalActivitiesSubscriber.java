package br.com.kproj.salesman.timeline.application.subscrivers;


import br.com.kproj.salesman.infrastructure.entity.builders.LogActivityBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.events.messages.RequestApprovalFinalizeMessage;
import br.com.kproj.salesman.infrastructure.events.messages.RequestNewApprovalMessage;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesApplication;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestApprovalActivitiesSubscriber {

    private static final String MESSAGE = "Solicitado aprovação de proposta";
    private static final String MESSAGE_FINALIZE = "O processo de aprovacao foi finalizado";

    @Autowired
    private TimelineActivitiesApplication application;


    @Subscribe
    public void generateActivityByRequestNewApproval(RequestNewApprovalMessage message) {
        LogActivity logActivity = LogActivityBuilder.createLogActivity()
                .withDescription(MESSAGE)
                .withType(LogActivityTypeEnum.START_APPROVAL)
                .withUser(message.getRequestApproval().getUserRequester()).build();

        application.register(message.getRequestApproval().getProposal(), logActivity);
    }

    @Subscribe
    public void generateActivityByFinalizeRequestApproval(RequestApprovalFinalizeMessage message) {
        LogActivity logActivity = LogActivityBuilder.createLogActivity()
                .withDescription(MESSAGE_FINALIZE)
                .withType(LogActivityTypeEnum.FINALIZE_APPROVAL)
                .withUser(message.getRequestApproval().getUserRequester()).build();

        application.register(message.getRequestApproval().getProposal(), logActivity);
    }

}
