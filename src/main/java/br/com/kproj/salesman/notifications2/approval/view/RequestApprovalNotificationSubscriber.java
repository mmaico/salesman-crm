package br.com.kproj.salesman.notifications2.approval.view;


import br.com.kproj.salesman.infrastructure.configuration.encapsulating.JsonSerializer;
import br.com.kproj.salesman.infrastructure.events.NewRequestApprovalMessage;
import br.com.kproj.salesman.infrastructure.events.messages.RequestNewApprovalMessage;
import br.com.kproj.salesman.notifications2.approval.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.notifications2.approval.domain.model.notification.Notification;
import br.com.kproj.salesman.notifications2.approval.domain.model.notification.NotificationBuilder;
import br.com.kproj.salesman.notifications2.approval.domain.model.notification.Notifications;
import br.com.kproj.salesman.notifications2.approval.domain.model.user.Receiver;
import br.com.kproj.salesman.notifications2.approval.view.dtos.RequestApprovalDTO;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestApprovalNotificationSubscriber {

    private JsonSerializer<RequestApprovalDTO> serializer;

    @Autowired
    public RequestApprovalNotificationSubscriber(JsonSerializer<RequestApprovalDTO> serializer) {
        this.serializer = serializer;
    }


    @Subscribe
    public void receiveRequestApproval(NewRequestApprovalMessage message) {
        RequestApprovalDTO approvalDTO = serializer.deserialize(message.getMessage(), RequestApprovalDTO.class);

        List<Notification> notificationList = approvalDTO.getApprovers().stream()
                .map(approver -> {
                    Negotiation negotiation = new Negotiation(approvalDTO.getNegotiation().getId());
                    return NotificationBuilder
                            .createView()
                            .createNow()
                            .withNegotiation(negotiation)
                            .withReceiver(new Receiver(approver.getApprover().getId())).build();
                }).collect(Collectors.toList());


        Notifications notifications = new Notifications(notificationList);

    }



}
