package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.notification.Notification;
import br.com.kproj.salesman.infrastructure.entity.notification.ScheduledTaskNotification;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.NotificationRepository;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.entity.builders.ApprovalBusinessProposalNotificationBuilder.createProposalNotification;

@Service
public class NotificationApplicationImpl extends BaseModelServiceImpl<Notification> implements NotificationApplication {


    @Autowired
    private NotificationRepository repository;

    @Autowired
    private UserRepository userRepository;

    public BaseRepository<Notification, Long> getRepository() {
        return repository;
    }

    @Override
    public void sendNotificationToProposalApprovers(RequestApproval requestApproval) {
        if(requestApproval.getApprovers().isEmpty() || requestApproval.getProposal().isNew()) {
            return;
        }
        List<Notification> notifications = Lists.newArrayList();

        requestApproval.getApprovers().stream()
                .forEach(approver ->
                        notifications.add(createProposalNotification()
                                .withBusinessProposal(requestApproval.getProposal())
                                .setCurrentDate()
                                .withNotified(approver.getApprover()).build())
                );

        repository.save(notifications);
    }

    @Override
    public void senScheduledTaskdNotification(ScheduledTaskNotification notification) {

        repository.save(notification);
    }
}
