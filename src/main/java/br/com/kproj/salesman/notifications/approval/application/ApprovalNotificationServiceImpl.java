package br.com.kproj.salesman.notifications.approval.application;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.notifications.approval.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.notifications.approval.domain.model.notification.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalNotificationServiceImpl extends BaseModelServiceImpl<Notification> implements ApprovalNotificationFacade {

    private NotificationRepository repository;

    @Autowired
    public ApprovalNotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void register(RequestNotification request) {
        Negotiation negotiation = request.getNegotiation();
        Receivers receivers = request.getReceivers();

        negotiation.inApproval().requestNotifications().to(receivers);
    }

    @Override
    public List<Notification> findAll(MyNotifications myNotifications) {
        return repository.findAll(myNotifications);
    }

    @Override
    public BaseRepository<Notification, Long> getRepository() {
        return repository;
    }

}
