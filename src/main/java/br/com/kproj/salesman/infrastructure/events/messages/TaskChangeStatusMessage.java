package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;

public class TaskChangeStatusMessage {


    private UserEntity user;
    private TaskStatusEntity oldStatus;
    private TaskEntity taskEntity;

    public TaskChangeStatusMessage(UserEntity user, TaskEntity taskEntity, TaskStatusEntity oldStatus) {
        this.user = user;
        this.taskEntity = taskEntity;
        this.oldStatus = oldStatus;
    }

    public static TaskChangeStatusMessage create(TaskEntity taskEntity, UserEntity userChange, TaskStatusEntity oldStatus) {
          return new TaskChangeStatusMessage(userChange, taskEntity, oldStatus);
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TaskStatusEntity getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(TaskStatusEntity oldStatus) {
        this.oldStatus = oldStatus;
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }
}
