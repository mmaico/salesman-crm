package br.com.kproj.salesman.assistants.calendar.view.support.builder;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.view.support.resource.ActivityResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.uol.rest.apiconverter.ConverterToResource;
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
public class ActivityResourceBuilder {

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

        ActivityResource resource = new ActivityResource();
        ConverterToResource.convert(activity, resource, context);

        return resource;
    }

}
