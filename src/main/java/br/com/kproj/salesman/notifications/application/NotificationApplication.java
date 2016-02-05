package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.notification.ApprovalBusinessProposalNotification;
import br.com.kproj.salesman.infrastructure.entity.notification.Notification;
import br.com.kproj.salesman.infrastructure.entity.notification.TaskNotification;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.service.ModelService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface NotificationApplication extends ModelService<Notification> {

    void sendNotificationToProposalApprovers(RequestApproval requestApproval);

    void sendScheduledTaskdNotification(TaskNotification notification);

    List<ApprovalBusinessProposalNotification> findProposalByUser(User user);

    List<TaskNotification> findTaskNotificationByUser(User user);

    Long findCountTaskNotificationBy(User user);

    Long findCountProposalBy(User user);
}
