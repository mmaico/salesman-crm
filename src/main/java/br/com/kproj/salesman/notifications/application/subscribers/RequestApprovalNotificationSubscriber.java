package br.com.kproj.salesman.notifications.application.subscribers;


import br.com.kproj.salesman.infrastructure.events.messages.RequestNewApprovalMessage;
import br.com.kproj.salesman.notifications.application.NotificationApplication;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestApprovalNotificationSubscriber {


    @Autowired
    private NotificationApplication application;


    @Subscribe
    public void generateNotificationToApprovers(RequestNewApprovalMessage message) {

        application.sendNotificationToProposalApprovers(message.getRequestApprovalEntity());

    }



}
