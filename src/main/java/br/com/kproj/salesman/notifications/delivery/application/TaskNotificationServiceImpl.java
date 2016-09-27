package br.com.kproj.salesman.notifications.delivery.application;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.notifications.delivery.domain.model.notification.Notification;
import br.com.kproj.salesman.notifications.delivery.domain.model.notification.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskNotificationServiceImpl extends BaseModelServiceImpl<Notification> implements TaskNotificationFacade {

    private NotificationRepository repository;

    @Autowired
    public TaskNotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }



    @Override
    public BaseRepository<Notification, Long> getRepository() {
        return repository;
    }

}
