package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks;

import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTask;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Subtask extends Task {

    private RootTask parent;

    public RootTask getParent() {
        return parent;
    }

    public void setParent(RootTask parent) {
        this.parent = parent;
    }
}
