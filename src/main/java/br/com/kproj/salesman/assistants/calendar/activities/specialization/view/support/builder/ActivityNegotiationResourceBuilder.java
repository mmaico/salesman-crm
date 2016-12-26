package br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.builder;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityNegotiation;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.resource.ActivityNegotiationResource;
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
public class ActivityNegotiationResourceBuilder {

    private static final String URI_NEGOTIATION = "/rs/customers/negotiations/{negotiationId}";
    private static final String URI_PARENT = "/rs/users/calendars/calendar-activities/{activityId}";

    @Autowired
    private HttpServletRequest request;

    public ResourceItem build(ActivityNegotiation activity) {
        ActivityNegotiationResource resource = buildItem(activity);

        return new ResourceItem(resource, getUri(request));
    }

    public ActivityNegotiationResource buildItem(ActivityNegotiation activity) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        String uriParent = URI_PARENT.replace("{activityId}", activity.getId().toString());
        String uriNegotiation = URI_NEGOTIATION.replace("{negotiationId}", activity.getNegotiation().getId().toString());

        context.addLinkConf(ActivityNegotiationResource.class, Link.createLink("parent", uriParent));
        context.addLinkConf(ActivityNegotiationResource.class, Link.createLink("of-negotiation", uriNegotiation));

        ActivityNegotiationResource resource = new ActivityNegotiationResource();
        ConverterToResource.convert(activity, resource, context);

        return resource;
    }

}
