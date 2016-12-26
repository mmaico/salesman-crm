package br.com.kproj.salesman.assistants.calendar.calendar.view.support.builder;



import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.calendar.view.support.resource.CalendarResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.apiconverter.configs.LinkAddConfig;
import br.com.uol.rest.apiconverter.resources.Link;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder.getUri;
import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class CalendarResourceBuilder {

    private static final String URI_ACTIVITIES =  "/rs/users/calendars/{calendarId}/calendar-activities";

    @Autowired
    private HttpServletRequest request;

    public ResourceItem build(Calendar calendar) {
        CalendarResource resource = buildItem(calendar);

        return new ResourceItem(resource, getUri(request));
    }

    public ResourceItems build(Iterable<Calendar> calendars) {

        List<CalendarResource> resources = Lists.newArrayList(calendars).stream()
                .map(item -> buildItem(item))
                .collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, getUri(request));
        ResourceHolder.setInfoPageable(calendars, resourceItems);

        return resourceItems;
    }

    public CalendarResource buildItem(Calendar calendar) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        CalendarResource resource = new CalendarResource();
        String uriActivities = URI_ACTIVITIES.replace("{calendarId}", calendar.getId().toString());

        context.addLinkConf(CalendarResource.class, Link.createLink("has-activities", uriActivities));
        ConverterToResource.convert(calendar, resource, context);

        return resource;
    }

}
