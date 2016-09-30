package br.com.kproj.salesman.notifications.logview.domain.service;

import br.com.kproj.salesman.notifications.logview.domain.model.view.NotificationType;

@FunctionalInterface
public interface SawNotificationTypeService {


    SawNotificationNowService of(NotificationType type);
}
