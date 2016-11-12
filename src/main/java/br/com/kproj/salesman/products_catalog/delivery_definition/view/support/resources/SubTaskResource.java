package br.com.kproj.salesman.products_catalog.delivery_definition.view.support.resources;


import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Subtask;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import br.com.uol.rest.infrastructure.annotations.SuperClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "task",
        "parent",
        "links"
})
@ResourceItem(name="subtask-definitions", modelReference = Subtask.class, parent = RootTaskResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubTaskResource extends Item {

    private Long id;

    @SuperClass
    private TaskResource task;

    private RootTaskResource parent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "is-a", expandByDefault = true)
    public TaskResource getTask() {
        return task;
    }

    public void setTask(TaskResource task) {
        this.task = task;
    }

    @Selectable(expression = "child-of")
    public RootTaskResource getParent() {
        return parent;
    }

    public void setParent(RootTaskResource parent) {
        this.parent = parent;
    }
}
