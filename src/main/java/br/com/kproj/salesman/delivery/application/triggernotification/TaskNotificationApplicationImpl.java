package br.com.kproj.salesman.delivery.application.triggernotification;

import br.com.kproj.salesman.infrastructure.entity.task.ScheduleTriggerNotification;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.events.messages.NewTaskTriggerToExecuteMessage;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.task.ScheduleTriggerNotificationRepository;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskNotificationApplicationImpl extends BaseModelServiceImpl<ScheduleTriggerNotification> implements TaskNotificationApplication {

    @Autowired
    private ScheduleTriggerNotificationRepository repository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EventBus eventBus;


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

    /**
       Executa a cada 30 min
     **/
    @Scheduled(fixedDelay= 1080000 )
    public void sendEventWhenTriggerAvailable() {

        List<ScheduleTriggerNotification> result = repository.findAllAvailableToday(DateHelper.now());

        for (ScheduleTriggerNotification trigger: result) {
            eventBus.post(NewTaskTriggerToExecuteMessage
                    .create(trigger.getTask(), trigger.getTriggerDate()));
            trigger.setExecuted(Boolean.TRUE);
        }
    }
}
