package br.com.kproj.salesman.assistants.calendar.view.support.resource.specialization;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.ActivityContact;
import br.com.kproj.salesman.assistants.calendar.view.support.resource.ActivityResource;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.services.Service;
import br.com.kproj.salesman.products_catalog.catalog.view.support.resources.SaleableResource;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import br.com.uol.rest.infrastructure.annotations.SuperClass;


@ResourceItem(name="activities-contacts", modelReference = ActivityContact.class, parent = ActivityResource.class)
public class ActivityContactResource extends Item {

    private Long id;

    @SuperClass
    private ActivityResource activity;

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

}
