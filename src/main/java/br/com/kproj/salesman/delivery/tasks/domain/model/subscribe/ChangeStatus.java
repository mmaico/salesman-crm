package br.com.kproj.salesman.delivery.tasks.domain.model.subscribe;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskStatus;
import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class ChangeStatus implements ValueObject {

    private final Long userId;
    private final Long taskId;
    private final TaskStatus newStatus;

    public ChangeStatus(Long userId, Long taskId, TaskStatus status) {
        this.userId = userId;
        this.taskId = taskId;
        this.newStatus = status;
    }


    public static ChangeStatus createStatus(Long userId, Long taskId, TaskStatus status) {
        return new ChangeStatus(userId, taskId, status);
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public TaskStatus getNewStatus() {
        return newStatus;
    }
}
