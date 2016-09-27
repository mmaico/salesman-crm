package br.com.kproj.salesman.notifications.approval.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.notifications.approval.domain.model.notification.MyNotifications;
import br.com.kproj.salesman.notifications.approval.domain.model.notification.Notification;
import br.com.kproj.salesman.notifications.approval.domain.model.notification.RequestNotification;

import java.util.List;

public interface ApprovalNotificationFacade extends ModelFacade<Notification> {

    void register(RequestNotification request);

    List<Notification> findAll(MyNotifications myNotifications);
}
