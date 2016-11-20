package br.com.kproj.salesman.delivery.tasks.view.support.resources;



import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
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
        "parent",
        "links"
})
@ResourceItem(name="subtasks", modelReference = Subtask.class, parent = TaskResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubtaskResource extends Item {

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

    @Selectable(expression = "child-of", externalLink = true)
    public RootTaskResource getParent() {
        return parent;
    }

    public void setParent(RootTaskResource parent) {
        this.parent = parent;
    }

    @JsonIgnore
    public Long getParentId() {
        return this.parent == null ? null : parent.getId();
    }

    @JsonIgnore
    public Long getTaskId() {
        return this.task == null ? null : task.getId();
    }


}
