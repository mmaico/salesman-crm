package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.task.Task;

public class TaskChangeMessage {


    private User user;
    private Task task;

    public TaskChangeMessage(User user, Task task) {
        this.user = user;
        this.task = task;
    }

    public static TaskChangeMessage create(Task task, User userChange) {
          return new TaskChangeMessage(userChange, task);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
