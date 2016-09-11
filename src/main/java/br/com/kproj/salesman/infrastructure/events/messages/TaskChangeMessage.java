package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;

public class TaskChangeMessage {


    private UserEntity user;
    private TaskEntity taskEntity;

    public TaskChangeMessage(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    public static TaskChangeMessage create(TaskEntity taskEntity) {
          return new TaskChangeMessage(taskEntity);
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }
}
