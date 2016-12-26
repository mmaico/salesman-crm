package br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.builder;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivitySaleable;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.resource.ActivitySaleableResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.apiconverter.resources.Link;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder.getUri;
import static br.com.uol.rest.apiconverter.configs.LinkRemoveConfig.createLinkRemoveConfig;
import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class ActivitySaleableResourceBuilder {

    private static final String URI_SALEABLE = "/rs/saleables/{saleableId}";
    private static final String URI_PARENT = "/rs/users/calendars/calendar-activities/{activityId}";

    @Autowired
    private HttpServletRequest request;

    public ResourceItem build(ActivitySaleable activity) {
        ActivitySaleableResource resource = buildItem(activity);

        return new ResourceItem(resource, getUri(request));
    }

    public ActivitySaleableResource buildItem(ActivitySaleable activity) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        String uriParent = URI_PARENT.replace("{activityId}", activity.getId().toString());
        String uriSaleable = URI_SALEABLE.replace("{saleableId}", activity.getSaleable().getId().toString());

        context.addLinkConf(ActivitySaleableResource.class, createLinkRemoveConfig("of-saleable"));
        context.addLinkConf(ActivitySaleableResource.class, Link.createLink("parent", uriParent));
        context.addLinkConf(ActivitySaleableResource.class, Link.createLink("of-saleable", uriSaleable));

        ActivitySaleableResource resource = new ActivitySaleableResource();
        ConverterToResource.convert(activity, resource, context);

        return resource;
    }

}
