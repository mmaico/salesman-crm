package br.com.kproj.salesman.products_catalog.delivery_definition.model.tasks;

import com.trex.shared.annotations.Model;

@Model
public class Subtask extends Task {

    private Task parent;

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }
}
