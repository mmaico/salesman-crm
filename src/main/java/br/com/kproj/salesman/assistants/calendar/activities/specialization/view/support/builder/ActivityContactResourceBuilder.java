package br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.builder;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityContact;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.resource.ActivityContactResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.apiconverter.resources.Link;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder.getUri;
import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class ActivityContactResourceBuilder {

    private static final String URI_PARENT = "/rs/users/calendars/calendar-activities/{activityId}";

    @Autowired
    private HttpServletRequest request;

    public ResourceItem build(ActivityContact activity) {
        ActivityContactResource resource = buildItem(activity);

        return new ResourceItem(resource, getUri(request));
    }

    public ActivityContactResource buildItem(ActivityContact activity) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        String uriParent = URI_PARENT.replace("{activityId}", activity.getId().toString());

        context.addLinkConf(ActivityContactResource.class, Link.createLink("parent", uriParent));
        ActivityContactResource resource = new ActivityContactResource();
        ConverterToResource.convert(activity, resource, context);

        return resource;
    }

}
