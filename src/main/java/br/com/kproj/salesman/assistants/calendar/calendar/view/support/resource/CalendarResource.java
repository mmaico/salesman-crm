package br.com.kproj.salesman.assistants.calendar.calendar.view.support.resource;


import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.calendar.Calendar;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "owner",
        "links"
})
@ResourceItem(name="calendars", modelReference = Calendar.class, parent = UserResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalendarResource extends Item {

    private Long id;

    private UserResource owner;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Selectable(expression = "owner", externalLink = true)
    public UserResource getOwner() {
        return owner;
    }

    public void setOwner(UserResource owner) {
        this.owner = owner;
    }

    public Long getOwnerId() {
        return this.owner == null ? null : this.owner.getId();
    }
}
