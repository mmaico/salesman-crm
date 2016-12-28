package br.com.kproj.salesman.assistants.activities.view.support.resource;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Status;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@ResourceItem(name="personal-activities", modelReference = Activity.class, parent = OwnerResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityResource extends Item {

    private Long id;
    private String title;
    private String description;
    private Date deadline;
    private Status status;
    private OwnerResource owner;
    private AssignerResource assigner;



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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Selectable(expression = "of-owner", externalLink = true)
    public OwnerResource getOwner() {
        return owner;
    }

    public void setOwner(OwnerResource owner) {
        this.owner = owner;
    }

    @Selectable(expression = "assigner-by", externalLink = true)
    public AssignerResource getAssigner() {
        return assigner;
    }

    public void setAssigner(AssignerResource assigner) {
        this.assigner = assigner;
    }
}
