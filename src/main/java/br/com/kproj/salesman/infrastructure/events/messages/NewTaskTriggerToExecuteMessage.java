package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.task.Task;

import java.util.Date;

public class NewTaskTriggerToExecuteMessage {

    private Task task;

    private Date triggerDate;

    private User userNotified;

    public NewTaskTriggerToExecuteMessage(Task task, Date triggerDate, User userNotified) {
        this.task = task;
        this.triggerDate = triggerDate;
        this.userNotified = userNotified;
    }

    public static NewTaskTriggerToExecuteMessage create(Task task, Date triggerDate, User userNotified) {
        return new NewTaskTriggerToExecuteMessage(task, triggerDate, userNotified);
    }

    public Task getTask() {
        return task;
    }

    public Date getTriggerDate() {
        return triggerDate;
    }

    public User getUserNotified() {
        return userNotified;
    }
}
