package br.com.kproj.salesman.timelines.activities.view.subscribers;


import org.springframework.stereotype.Component;

@Component
public class RequestApprovalActivitiesSubscriber {

    static final String MESSAGE = "Solicitado aprovação de proposta";
    static final String MESSAGE_FINALIZE = "O processo de aprovacao foi finalizado";


//    @Subscribe
//    public void generateActivityByRequestNewApproval(RequestNewApprovalMessage message) {
//        LogActivity logActivity = LogActivityBuilder.createLogActivity()
//                .withDescription(MESSAGE)
//                .withType(LogActivityTypeEnum.START_APPROVAL)
//                .withUser(message.getRequestApprovalEntity().getUserRequester()).build();
//
//        application.register(message.getRequestApprovalEntity().getProposal(), logActivity);
//    }
//
//    @Subscribe
//    public void generateActivityByFinalizeRequestApproval(RequestApprovalFinalizeMessage message) {
//        LogActivity logActivity = LogActivityBuilder.createLogActivity()
//                .withDescription(MESSAGE_FINALIZE)
//                .withType(LogActivityTypeEnum.FINALIZE_APPROVAL)
//                .withUser(message.getRequestApprovalEntity().getUserRequester()).build();
//
//        application.register(message.getRequestApprovalEntity().getProposal(), logActivity);
//    }

}
