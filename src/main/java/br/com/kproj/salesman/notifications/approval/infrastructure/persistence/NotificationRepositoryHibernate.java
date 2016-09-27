package br.com.kproj.salesman.notifications.approval.infrastructure.persistence;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.notification.ApprovalBusinessProposalNotification;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.notifications.approval.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.notifications.approval.domain.model.notification.*;
import br.com.kproj.salesman.notifications.approval.domain.model.user.Receiver;
import br.com.kproj.salesman.notifications.approval.infrastructure.persistence.springdata.NotificationRepositorySpringdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.notifications.approval.domain.model.notification.NotificationBuilder.createView;

@Repository
public class NotificationRepositoryHibernate extends BaseRespositoryImpl<Notification, ApprovalBusinessProposalNotification> implements NotificationRepository {

    private NotificationRepositorySpringdata repository;

    @Autowired
    public NotificationRepositoryHibernate(NotificationRepositorySpringdata repository) {
        this.repository = repository;
    }

    @Override
    public void register(RequestNotification request) {
        List<ApprovalBusinessProposalNotification> notifications = request.getReceivers().stream().map(receiver -> {
            ApprovalBusinessProposalNotification notification = new ApprovalBusinessProposalNotification();
            notification.setProposal(new BusinessProposalEntity(request.getNegotiation().getId()));
            notification.setCreateDate(new Date());
            notification.setNotified(new UserEntity(receiver.getId()));
            return notification;
        }).collect(Collectors.toList());

        repository.save(notifications);

    }

    @Override
    public List<Notification> findAll(MyNotifications myNotifications) {
        List<ApprovalBusinessProposalNotification> result = repository.findProposalNotificationBy(new UserEntity(myNotifications.getUser().getId()));
        return result.stream().map(item -> getConverter().convert(item)).collect(Collectors.toList());
    }

    @Override
    public BaseRepositoryLegacy<ApprovalBusinessProposalNotification, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ApprovalBusinessProposalNotification, Notification> getConverter() {
        return (notificationEntity, args) ->
            createView(notificationEntity.getId())
                    .withReceiver(new Receiver(notificationEntity.getNotified().getId()))
                    .withNegotiation(new Negotiation(notificationEntity.getProposal().getId()))
                    .withCreation(notificationEntity.getCreateDate()).build();
    }
}
