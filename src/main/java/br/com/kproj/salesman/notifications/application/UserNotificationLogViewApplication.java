package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogView;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.Optional;


public interface UserNotificationLogViewApplication extends ModelLegacyService<UserNotificationLogView> {

    Optional<UserNotificationLogView> register(UserNotificationLogView view);

    Optional<UserNotificationLogView> getLastViewProposalNotification(User user);

    Optional<UserNotificationLogView> getLastViewTaskNotification(User user);
}
