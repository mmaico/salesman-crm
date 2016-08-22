package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.task.Task;

public class TaskChangeMessage {


    private UserEntity user;
    private Task task;

    public TaskChangeMessage(Task task) {
        this.task = task;
    }

    public static TaskChangeMessage create(Task task) {
          return new TaskChangeMessage(task);
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
