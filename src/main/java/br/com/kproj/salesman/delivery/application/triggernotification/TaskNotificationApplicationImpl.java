package br.com.kproj.salesman.delivery.application.triggernotification;

import br.com.kproj.salesman.infrastructure.entity.notification.ScheduleTriggerNotification;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.task.ScheduleTriggerNotificationRepository;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskNotificationApplicationImpl extends BaseModelServiceImpl<ScheduleTriggerNotification> implements TaskNotificationApplication {

    @Autowired
    private ScheduleTriggerNotificationRepository repository;

    @Autowired
    private TaskRepository taskRepository;


    public BaseRepository<ScheduleTriggerNotification, Long> getRepository() {
        return repository;
    }


    @Override
    public ScheduleTriggerNotification register(ScheduleTriggerNotification notification) {

        if (!notification.isValidTrigger()) {
            throw new ValidationException(Sets.newHashSet("task.notification.invalid.date"));
        }
        Optional<Task> taskLoaded = taskRepository.getOne(notification.getTask().getId());

        if(!taskLoaded.isPresent()) {
            throw new ValidationException(Sets.newHashSet("task.notification.invalid.task"));
        }

        Task task = taskLoaded.get();
        if (!task.getTriggerNotifications().isEmpty()) {
            task.getTriggerNotifications().forEach(value -> repository.delete(value));
            task.getTriggerNotifications().clear();

        }
        ScheduleTriggerNotification triggerSaved = repository.save(notification);

        task.addTriggerNotification(triggerSaved);

        return triggerSaved;
    }
}
