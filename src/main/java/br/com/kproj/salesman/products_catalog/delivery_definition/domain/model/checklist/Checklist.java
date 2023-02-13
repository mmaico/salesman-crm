package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Checklist extends ModelIdentifiable {

    private Long id;
    private String name;
    private Task task;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
