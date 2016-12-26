package br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.resource;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivitySaleable;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="activities-saleables", modelReference = ActivitySaleable.class, parent = ActivityResource.class)
public class ActivitySaleableResource extends Item {

    private Long id;

    private SaleableResource saleable;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "of-saleable")
    public SaleableResource getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableResource saleable) {
        this.saleable = saleable;
    }

    @JsonIgnore
    public Long getSaleableId() {
        return saleable == null ? null : saleable.getId();
    }

}
