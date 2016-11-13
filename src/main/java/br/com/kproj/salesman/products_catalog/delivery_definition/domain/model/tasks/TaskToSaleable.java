package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks;

import br.com.kproj.salesman.infrastructure.model.ValueObject;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;


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

    public Saleable getAsASaleable() {
        return new Saleable(saleableId);
    }

    public static TaskToSaleable createTaskToSaleable(Long saleableId, Task task) {
        return new TaskToSaleable(saleableId, task);
    }
}
