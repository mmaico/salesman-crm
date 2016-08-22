package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.task.Task;

public class TaskChangeStatusMessage {


    private UserEntity user;
    private TaskStatus oldStatus;
    private Task task;

    public TaskChangeStatusMessage(UserEntity user, Task task, TaskStatus oldStatus) {
        this.user = user;
        this.task = task;
        this.oldStatus = oldStatus;
    }

    public static TaskChangeStatusMessage create(Task task, UserEntity userChange, TaskStatus oldStatus) {
          return new TaskChangeStatusMessage(userChange, task, oldStatus);
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
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
