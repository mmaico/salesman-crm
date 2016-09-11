package br.com.kproj.salesman.delivery2.tasks.domain.model.user;

import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class Subscribe implements ValueObject {

    private final User user;
    private final Task task;

    public Subscribe(User user, Task task) {
        this.user = user;
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public Task getTask() {
        return task;
    }

    public static Subscribe createSubscribe(User user, Task task) {
        return new Subscribe(user, task);
    }
}
