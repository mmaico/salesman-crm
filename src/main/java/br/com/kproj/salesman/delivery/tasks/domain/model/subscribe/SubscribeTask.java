package br.com.kproj.salesman.delivery.tasks.domain.model.subscribe;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class SubscribeTask implements ValueObject {

    private final Long userId;
    private final Long taskId;

    public SubscribeTask(Long userId, Long taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public static SubscribeTask createSubscriber(Long userId, Long taskId) {
        return new SubscribeTask(userId, taskId);
    }

    public User getUser() {
        return new User(this.userId);
    }

    public Task getTask() {
        return new Task(taskId);
    }
}
