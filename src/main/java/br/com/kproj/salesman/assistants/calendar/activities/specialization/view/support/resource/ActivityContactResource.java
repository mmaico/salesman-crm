package br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.resource;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityContact;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="activities-contacts", modelReference = ActivityContact.class, parent = ActivityResource.class)
public class ActivityContactResource extends Item {

    private Long id;

    private ContactResource contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "of-contact", externalLink = true)
    public ContactResource getContact() {
        return contact;
    }

    public void setContact(ContactResource contact) {
        this.contact = contact;
    }

    @JsonIgnore
    public Long getContactId() {
        return contact == null ? null : contact.getId();
    }

}
