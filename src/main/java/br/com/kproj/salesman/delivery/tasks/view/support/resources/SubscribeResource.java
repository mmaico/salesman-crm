package br.com.kproj.salesman.delivery.tasks.view.support.resources;


import br.com.kproj.salesman.delivery.delivery.view.support.resources.UserResource;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="tasks", modelReference = Subscriber.class, parent = TaskResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscribeResource extends Item {

    private Long id;
    private TaskResource task;
    private UserResource user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "has-task", externalLink = true)
    public TaskResource getTask() {
        return task;
    }

    public void setTask(TaskResource task) {
        this.task = task;
    }

    @Selectable(expression = "has-user", externalLink = true)
    public UserResource getUser() {
        return user;
    }

    public void setUser(UserResource user) {
        this.user = user;
    }

    public Long getUserId () {
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public Long getTaskId() {
        if (task == null) {
            return null;
        }
        return task.getId();
    }
}
