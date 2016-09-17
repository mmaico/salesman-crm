package br.com.kproj.salesman.delivery.tasks_template.model.tasks;

import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class TaskToSaleable implements ValueObject {

    private final Long saleableId;
    private final Task task;

    public TaskToSaleable(Long saleableId, Task task) {
        this.saleableId = saleableId;
        this.task = task;
    }

    public Long getSaleableId() {
        return saleableId;
    }

    public Task getTask() {
        return task;
    }
}
