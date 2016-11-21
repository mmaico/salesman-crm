package br.com.kproj.salesman.delivery.tasks.view.support.resources;



import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import br.com.uol.rest.infrastructure.annotations.SuperClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "task",
        "links"
})
@ResourceItem(name="root-tasks", modelReference = RootTask.class, parent = TaskResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootTaskResource extends Item {

    private Long id;

    @SuperClass
    private TaskResource task;

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

    @JsonIgnore
    public Long getTaskId() {
        return this.task == null ? null : this.task.getId();
    }
}
