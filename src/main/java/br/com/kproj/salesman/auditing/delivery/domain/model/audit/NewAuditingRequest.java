package br.com.kproj.salesman.auditing.delivery.domain.model.audit;


import br.com.kproj.salesman.auditing.delivery.domain.model.task.Task;
import br.com.kproj.salesman.auditing.delivery.domain.model.user.User;

public class NewAuditingRequest {

    private final User user;
    private final Task task;

    public NewAuditingRequest(User user, Task task) {
        this.user = user;
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public Task getTask() {
        return task;
    }
}
