package br.com.kproj.salesman.delivery.tasks.domain.model.user;

import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class Subscribe implements ValueObject {

    private final Long userId;
    private final Long taskId;

    public Subscribe(Long userId, Long taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public static Subscribe createSubscribe(Long userId, Long taskId) {
        return new Subscribe(userId, taskId);
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTaskId() {
        return taskId;
    }
}
