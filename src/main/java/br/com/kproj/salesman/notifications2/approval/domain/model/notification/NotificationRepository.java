package br.com.kproj.salesman.notifications2.approval.domain.model.notification;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.List;

public interface NotificationRepository extends BaseRepository<Notification, Long> {


    void register(RequestNotification request);

    List<Notification> findAll(MyNotifications myNotifications);

}

