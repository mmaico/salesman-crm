package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks;

import br.com.kproj.salesman.infrastructure.model.ValueObject;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;


public class SubtaskToRootTask implements ValueObject {

    private final Long rootTaskId;
    private final Subtask subtask;

    public SubtaskToRootTask(Long rootTaskId, Subtask subtask) {
        this.rootTaskId = rootTaskId;
        this.subtask = subtask;
    }

    public Long getRootTaskId() {
        return rootTaskId;
    }

    public Subtask getSubtask() {
        return subtask;
    }

    public RootTask getAsRootTask() {
        return new RootTask(this.rootTaskId);
    }
}
