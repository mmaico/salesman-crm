package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;

public class TaskChangeStatusMessage {


    private UserEntity user;
    private TaskStatus oldStatus;
    private TaskEntity taskEntity;

    public TaskChangeStatusMessage(UserEntity user, TaskEntity taskEntity, TaskStatus oldStatus) {
        this.user = user;
        this.taskEntity = taskEntity;
        this.oldStatus = oldStatus;
    }

    public static TaskChangeStatusMessage create(TaskEntity taskEntity, UserEntity userChange, TaskStatus oldStatus) {
          return new TaskChangeStatusMessage(userChange, taskEntity, oldStatus);
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

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }
}
