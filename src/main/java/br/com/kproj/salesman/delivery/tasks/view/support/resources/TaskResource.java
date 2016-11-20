package br.com.kproj.salesman.delivery.tasks.view.support.resources;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskStatus;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collection;
import java.util.Date;

@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "deadline",
        "status",
        "checklists",
        "delivery",
        "links"
})
@ResourceItem(name="tasks", modelReference = Task.class, parent = DeliveryResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskResource extends Item {

    private Long id;
    private String title;
    private String description;
    private Date deadline;
    private TaskStatus status;
    private Collection<ChecklistResource> checklists;
    private DeliveryResource delivery;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Selectable(expression = "has-checklists", externalLink = true)
    public Collection<ChecklistResource> getChecklists() {
        return checklists;
    }

    public void setChecklists(Collection<ChecklistResource> checklists) {
        this.checklists = checklists;
    }

    @Selectable(expression = "of-delivery", externalLink = true)
    public DeliveryResource getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryResource delivery) {
        this.delivery = delivery;
    }

    public Long getDeliveryId() {
        if (delivery == null) return null;

        return delivery.getId();
    }
}
