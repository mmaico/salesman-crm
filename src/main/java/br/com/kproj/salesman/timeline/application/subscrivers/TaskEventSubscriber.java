package br.com.kproj.salesman.timeline.application.subscrivers;


import br.com.kproj.salesman.infrastructure.entity.builders.TimelineActivityBuilder;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TaskActivity;
import br.com.kproj.salesman.infrastructure.events.messages.TaskChangeStatusMessage;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesApplication;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskEventSubscriber {

    static final String MESSAGE = "Alterado do status da tarefa de: {OLD} para: {NEW}";

    @Autowired
    private TimelineActivitiesApplication application;


    @Subscribe
    public void createActivityTimelineOnChangeTaskStatus(TaskChangeStatusMessage event) {
        String message = MESSAGE.replace("{OLD}", event.getOldStatus().get())
                                .replace("{NEW}", event.getTaskEntity().getStatus().get());

        TaskActivity taskActivity = TimelineActivityBuilder.createActivity()
                .withDescription(message)
                .withUser(event.getUser().getId()).build();

        application.register(event.getTaskEntity(), taskActivity);

    }

}
