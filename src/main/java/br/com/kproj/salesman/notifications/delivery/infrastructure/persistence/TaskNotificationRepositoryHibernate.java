package br.com.kproj.salesman.notifications.delivery.infrastructure.persistence;



import br.com.kproj.salesman.infrastructure.entity.notification.TaskNotification;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.notifications.delivery.domain.model.notification.Notification;
import br.com.kproj.salesman.notifications.delivery.domain.model.notification.NotificationRepository;
import br.com.kproj.salesman.notifications.delivery.domain.model.task.Task;
import br.com.kproj.salesman.notifications.delivery.domain.model.user.Receiver;
import br.com.kproj.salesman.notifications.delivery.infrastructure.persistence.springdata.TaskNotificationRepositorySpringdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static br.com.kproj.salesman.notifications.delivery.domain.model.notification.NotificationBuilder.createView;


@Repository
public class TaskNotificationRepositoryHibernate extends BaseRespositoryImpl<Notification, TaskNotification> implements NotificationRepository {

    private TaskNotificationRepositorySpringdata repository;

    @Autowired
    public TaskNotificationRepositoryHibernate(TaskNotificationRepositorySpringdata repository) {
        this.repository = repository;
    }


    @Override
    public BaseRepositoryLegacy<TaskNotification, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<TaskNotification, Notification> getConverter() {
        return (notificationEntity, args) ->
            createView(notificationEntity.getId())
                    .withReceiver(new Receiver(notificationEntity.getNotified().getId()))
                    .withTask(new Task(notificationEntity.getTask().getId()))
                    .withCreation(notificationEntity.getCreateDate()).build();
    }
}
