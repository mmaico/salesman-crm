package br.com.kproj.salesman.assistants.activities.view.support.resource;



import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivity;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import br.com.uol.rest.infrastructure.annotations.SuperClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "activity",
        "parent",
        "links"
})
@ResourceItem(name="personal-sub-activities", modelReference = SubActivity.class, parent = ActivityResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubActivityResource extends Item {

    private Long id;

    @SuperClass
    private ActivityResource activity;

    private RootActivityResource parent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "is-a", expandByDefault = true)
    public ActivityResource getActivity() {
        return activity;
    }

    public void setActivity(ActivityResource activity) {
        this.activity = activity;
    }

    @Selectable(expression = "child-of", externalLink = true)
    public RootActivityResource getParent() {
        return parent;
    }

    public void setParent(RootActivityResource parent) {
        this.parent = parent;
    }


    @JsonIgnore
    public Long getActivityId() {
        return this.activity == null ? null : activity.getId();
    }


}
