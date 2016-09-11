package br.com.kproj.salesman.infrastructure.entity.notification;


import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("task_notification")
public class TaskNotification extends Notification {

    /**
     *
     */
    private static final long serialVersionUID = 2728388686834419769L;

    @ManyToOne
    @JoinColumn(name="task_id")
    private TaskEntity taskEntity;

    public TaskEntity getTask() {
        return taskEntity;
    }

    public void setTask(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }
}
