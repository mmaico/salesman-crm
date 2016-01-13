package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.notification.Notification;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.service.ModelService;


public interface NotificationApplication extends ModelService<Notification> {

    void sendNotificationToProposalApprovers(RequestApproval requestApproval);
}
