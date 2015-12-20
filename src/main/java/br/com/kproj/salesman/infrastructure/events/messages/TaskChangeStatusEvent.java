package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.task.Task;

public class TaskChangeStatusEvent {


    private User user;
    private TaskStatus oldStatus;
    private Task task;

    public TaskChangeStatusEvent(User user, Task task, TaskStatus oldStatus) {
        this.user = user;
        this.task = task;
        this.oldStatus = oldStatus;
    }

    public static TaskChangeStatusEvent create(Task task, User userChange, TaskStatus oldStatus) {
          return new TaskChangeStatusEvent(userChange, task, oldStatus);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskStatus getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(TaskStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
