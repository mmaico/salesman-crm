package br.com.kproj.salesman.notifications.application.subscribers;


import br.com.kproj.salesman.infrastructure.entity.builders.LogActivityBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.events.messages.RequestApprovalFinalizeMessage;
import br.com.kproj.salesman.infrastructure.events.messages.RequestNewApprovalMessage;
import br.com.kproj.salesman.notifications.application.NotificationApplication;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesApplication;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestApprovalNotificationSubscriber {



    @Autowired
    private NotificationApplication application;


    @Subscribe
    public void generateNotificationToApprovers(RequestNewApprovalMessage message) {
        //message.getRequestApproval().getApprovers()
        //message.getRequestApproval().getProposal()

    }



}
