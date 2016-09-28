package br.com.kproj.salesman.delivery.tasks.domain.model.user;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class SubscribeTask implements ValueObject {

    private final User user;
    private final Task task;

    public SubscribeTask(User user, Task task) {
        this.user = user;
        this.task = task;
    }

    public static SubscribeTask createSubscribe(User user, Task task) {
        return new SubscribeTask(user, task);
    }

    public User getUser() {
        return user;
    }

    public Task getTask() {
        return task;
    }
}
