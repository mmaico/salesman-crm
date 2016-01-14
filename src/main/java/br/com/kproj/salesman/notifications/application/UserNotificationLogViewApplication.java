package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogView;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.Optional;


public interface UserNotificationLogViewApplication extends ModelService<UserNotificationLogView> {

    Optional<UserNotificationLogView> register(UserNotificationLogView view);
}
