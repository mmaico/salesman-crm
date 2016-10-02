package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;

import java.util.Date;

@Deprecated
public class NewTaskTriggerToExecuteMessage {

    private TaskEntity taskEntity;

    private Date triggerDate;

    private UserEntity userNotified;

    public NewTaskTriggerToExecuteMessage(TaskEntity taskEntity, Date triggerDate, UserEntity userNotified) {
        this.taskEntity = taskEntity;
        this.triggerDate = triggerDate;
        this.userNotified = userNotified;
    }

    public static NewTaskTriggerToExecuteMessage create(TaskEntity taskEntity, Date triggerDate, UserEntity userNotified) {
        return new NewTaskTriggerToExecuteMessage(taskEntity, triggerDate, userNotified);
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public Date getTriggerDate() {
        return triggerDate;
    }

    public UserEntity getUserNotified() {
        return userNotified;
    }
}
