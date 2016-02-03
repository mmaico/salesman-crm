package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.task.Task;

import java.util.Date;

public class NewTaskTriggerToExecuteMessage {

    private Task task;

    private Date triggerDate;

    public NewTaskTriggerToExecuteMessage(Task task, Date triggerDate) {
        this.task = task;
        this.triggerDate = triggerDate;
    }

    public static NewTaskTriggerToExecuteMessage create(Task task, Date triggerDate) {
        return new NewTaskTriggerToExecuteMessage(task, triggerDate);
    }

}
