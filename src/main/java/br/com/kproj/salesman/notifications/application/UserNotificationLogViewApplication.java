package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogView;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.Optional;


public interface UserNotificationLogViewApplication extends ModelLegacyService<UserNotificationLogView> {

    Optional<UserNotificationLogView> register(UserNotificationLogView view);

    Optional<UserNotificationLogView> getLastViewProposalNotification(UserEntity user);

    Optional<UserNotificationLogView> getLastViewTaskNotification(UserEntity user);
}
