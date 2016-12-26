package br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.resource;



import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.negotiation.Negotiation;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="negotiations", modelReference = Negotiation.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NegotiationResource extends Item {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
