package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.notification.NotificationView;
import br.com.kproj.salesman.infrastructure.entity.notification.ProposalNotificationView;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.Optional;


public interface ProposalNotificationViewApplication extends ModelService<NotificationView> {

    Optional<NotificationView> register(NotificationView view);
}
