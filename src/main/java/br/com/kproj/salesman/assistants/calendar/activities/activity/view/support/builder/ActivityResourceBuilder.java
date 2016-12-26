package br.com.kproj.salesman.assistants.calendar.activities.activity.view.support.builder;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.Represent;
import br.com.kproj.salesman.assistants.calendar.view.support.resource.ActivityResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.apiconverter.resources.Link;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.Represent.*;
import static br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder.getUri;
import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class ActivityResourceBuilder {

    private static final String BASE_URI = "/rs/users/calendars/calendar-activities/calendar-activities-";

    private static Map<Represent, SelectLink> selectLink = new HashMap<>();

    static {
        selectLink.put(CONTACT, (activity ->
            Link.createLink("calendar-activities-contact", BASE_URI + "contacts/" + activity.getId())
        ));

        selectLink.put(NEGOTIATION, (activity ->
            Link.createLink("calendar-activities-negotiation", BASE_URI + "negotiations/" + activity.getId())
        ));

        selectLink.put(SALEABLE, (activity ->
                Link.createLink("calendar-activities-saleable", BASE_URI + "saleables/" + activity.getId())
        ));

        selectLink.put(NO_REPRESENT, (activity -> null));
    }

    @Autowired
    private HttpServletRequest request;

    public ResourceItem build(Activity activity) {
        ActivityResource resource = buildItem(activity);

        return new ResourceItem(resource, getUri(request));
    }

    public ResourceItems build(Iterable<Activity> activities) {

        List<ActivityResource> resources = Lists.newArrayList(activities).stream()
                .map(item -> buildItem(item))
                .collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, getUri(request));
        ResourceHolder.setInfoPageable(activities, resourceItems);

        return resourceItems;
    }

    public ActivityResource buildItem(Activity activity) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        Link linkSpecialization = selectLink.get(activity.getRepresent()).select(activity);
        context.addLinkConf(ActivityResource.class, linkSpecialization);

        ActivityResource resource = new ActivityResource();
        ConverterToResource.convert(activity, resource, context);

        return resource;
    }

    private interface SelectLink {

        Link select(Activity activity);
    }

}
