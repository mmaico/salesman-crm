package br.com.kproj.salesman.timeline.application.events;


import br.com.kproj.salesman.infrastructure.entity.builders.TimelineActivityBuilder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TaskActivity;
import br.com.kproj.salesman.infrastructure.events.messages.TaskChangeStatusEvent;
import br.com.kproj.salesman.infrastructure.repository.task.TaskActivityRepository;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskEventSubscriber {

    private static final String MESSAGE = "Alterado do status da tarefa de: {OLD} para: {NEW}";

    @Autowired
    private TaskActivityRepository activityRepository;

    @Autowired
    private TaskRepository taskRepository;



    @Subscribe
    public void createActivityTimelineOnChangeTaskStatus(TaskChangeStatusEvent event) {
        String message = MESSAGE.replace("{OLD}", event.getOldStatus().get())
                                .replace("{NEW}", event.getTask().getStatus().get());

        TaskActivity taskActivity = TimelineActivityBuilder.createActivity()
                .withDescription(message)
                .withUser(event.getUser().getId()).build();

        Task taskLoaded = taskRepository.findOne(event.getTask().getId());

        activityRepository.save(taskActivity);
        taskLoaded.getTimeline().getActivities().add(taskActivity);
    }

}
