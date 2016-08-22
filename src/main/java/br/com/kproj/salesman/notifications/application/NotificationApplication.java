package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.notification.ApprovalBusinessProposalNotification;
import br.com.kproj.salesman.infrastructure.entity.notification.Notification;
import br.com.kproj.salesman.infrastructure.entity.notification.TaskNotification;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;


public interface NotificationApplication extends ModelLegacyService<Notification> {

    void sendNotificationToProposalApprovers(RequestApproval requestApproval);

    void sendScheduledTaskdNotification(TaskNotification notification);

    List<ApprovalBusinessProposalNotification> findProposalByUser(UserEntity user);

    List<TaskNotification> findTaskNotificationByUser(UserEntity user);

    Long findCountTaskNotificationBy(UserEntity user);

    Long findCountProposalBy(UserEntity user);
}
