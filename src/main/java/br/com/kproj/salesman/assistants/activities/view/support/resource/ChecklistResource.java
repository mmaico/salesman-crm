package br.com.kproj.salesman.assistants.activities.view.support.resource;



import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name",
        "done",
        "links"
})
@ResourceItem(name="activities-checklists", modelReference = Checklist.class, parent = ActivityResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChecklistResource extends Item {

    private Long id;
    private String name;
    private Boolean done;

    private ActivityResource activity;

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

    @Selectable(expression = "of-activity", externalLink = true)
    public ActivityResource getActivity() {
        return activity;
    }

    public void setActivity(ActivityResource activity) {
        this.activity = activity;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @JsonIgnore
    public Long getActivityId() {
        return this.activity == null ? null : this.activity.getId();
    }
}
