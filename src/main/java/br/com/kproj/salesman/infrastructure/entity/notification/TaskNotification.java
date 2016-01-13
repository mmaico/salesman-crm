package br.com.kproj.salesman.infrastructure.entity.notification;


import br.com.kproj.salesman.infrastructure.entity.task.Task;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("task_notification")
public class TaskNotification extends Notification {

    /**
     *
     */
    private static final long serialVersionUID = 2728388686834419769L;

    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
