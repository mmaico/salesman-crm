package br.com.kproj.salesman.delivery.application.triggernotification;

import br.com.kproj.salesman.delivery.application.WorkspaceApplication;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.task.ScheduleTriggerNotification;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.events.messages.NewTaskTriggerToExecuteMessage;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.task.ScheduleTriggerNotificationRepository;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskNotificationApplicationImpl extends BaseModelServiceLegacyImpl<ScheduleTriggerNotification> implements TaskNotificationApplication {

    @Autowired
    private ScheduleTriggerNotificationRepository repository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private WorkspaceApplication workspaceApplication;

    @Autowired
    private EventBus eventBus;


    public BaseRepositoryLegacy<ScheduleTriggerNotification, Long> getRepository() {
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
       Executa a cada 10 min
     **/
    @Scheduled(fixedDelay= 600000 )
    public void sendEventWhenTriggerAvailable() {

        List<ScheduleTriggerNotification> result = repository.findAllAvailableToday(DateHelper.now());

        for (ScheduleTriggerNotification trigger: result) {
            List<User> users = workspaceApplication.findUsersResponsibles(trigger.getTask().getSalesOrder());
            users.forEach(user ->
                    eventBus.post(NewTaskTriggerToExecuteMessage
                            .create(trigger.getTask(), trigger.getTriggerDate(), user))
            );
            trigger.setExecuted(Boolean.TRUE);
        }
    }
}
