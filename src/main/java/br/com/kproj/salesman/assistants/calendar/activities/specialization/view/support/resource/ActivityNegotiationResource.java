package br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.resource;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityNegotiation;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="activities-negotiations", modelReference = ActivityNegotiation.class, parent = ActivityResource.class)
public class ActivityNegotiationResource extends Item {

    private Long id;

    private NegotiationResource negotiation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NegotiationResource getNegotiation() {
        return negotiation;
    }

    public void setContact(NegotiationResource negotiation) {
        this.negotiation = negotiation;
    }

    @JsonIgnore
    public Long getNegotiationId() {
        return negotiation == null ? null : negotiation.getId();
    }

}
