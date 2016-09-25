package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogViewEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.Optional;


public interface UserNotificationLogViewApplication extends ModelLegacyService<UserNotificationLogViewEntity> {

    Optional<UserNotificationLogViewEntity> register(UserNotificationLogViewEntity view);

    Optional<UserNotificationLogViewEntity> getLastViewProposalNotification(UserEntity user);

    Optional<UserNotificationLogViewEntity> getLastViewTaskNotification(UserEntity user);
}
