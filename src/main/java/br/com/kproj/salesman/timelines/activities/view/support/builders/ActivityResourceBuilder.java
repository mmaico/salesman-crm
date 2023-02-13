package br.com.kproj.salesman.timelines.activities.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;
import br.com.kproj.salesman.timelines.activities.view.support.resources.ActivityResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.apiconverter.resources.Link;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.uol.rest.apiconverter.resources.Link.createLink;
import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component("activityResourceBuilderTimelineActivitiesModule")
public class ActivityResourceBuilder {

    private static final String URL_MEDIAS = "/rs/timelines/activities/{activityId}/activities-medias-relationships";

    @Autowired
    private HttpServletRequest request;


    public ResourceItem build(Activity activity) {
        ActivityResource resource = buildItem(activity);

        return new ResourceItem(resource, request.getRequestURI());
    }

    public ResourceItems build(Iterable<Activity> activities) {

        List<ActivityResource> resources = Lists.newArrayList(activities).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, request.getRequestURI());
    }

    public ActivityResource buildItem(Activity activity) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);
        context.addLinkConf(ActivityResource.class, createLink("has-medias", URL_MEDIAS.replace("{activityId}", activity.getId().toString())));

        ActivityResource resource = new ActivityResource();
        ConverterToResource.convert(activity, resource, context);

        return resource;
    }

}
