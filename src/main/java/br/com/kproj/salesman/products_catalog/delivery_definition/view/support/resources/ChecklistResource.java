package br.com.kproj.salesman.products_catalog.delivery_definition.view.support.resources;


import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.Checklist;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name",
        "links"
})
@ResourceItem(name="checklist-definitions", modelReference = Checklist.class, parent = TaskResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChecklistResource extends Item {

    private Long id;
    private String name;

    private TaskResource task;

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

    @Selectable(expression = "of-task", externalLink = true)
    public TaskResource getTask() {
        return task;
    }

    public void setTask(TaskResource task) {
        this.task = task;
    }

    public Long getTaskId() {
        return task == null ? null : task.getId();
    }

}
