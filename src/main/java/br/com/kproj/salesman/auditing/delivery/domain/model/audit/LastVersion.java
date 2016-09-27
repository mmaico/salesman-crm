package br.com.kproj.salesman.auditing.delivery.domain.model.audit;


import br.com.kproj.salesman.auditing.delivery.domain.model.task.Task;
import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class LastVersion implements ValueObject {

    private final Task task;


    public LastVersion(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public static LastVersion of(Task task) {
        return new LastVersion(task);
    }
}
