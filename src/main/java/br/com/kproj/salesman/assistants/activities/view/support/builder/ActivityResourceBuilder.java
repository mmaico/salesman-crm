package br.com.kproj.salesman.assistants.activities.view.support.builder;



import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Represent;
import br.com.kproj.salesman.assistants.activities.view.support.resource.ActivityResource;
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

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component("activityResourceBuilderActivitiesModule")
public class ActivityResourceBuilder {

    private static final String URI_CHECKLISTS = "/rs/users/personal-activities/{activityId}/activities-checklists";

    @Autowired
    private HttpServletRequest request;

    private static Map<Represent, SelectLink> selectLink = new HashMap<>();

    static {
        selectLink.put(Represent.ROOTACTIVITY, ((activity, context) -> {
            Link link = Link.createLink("is-a-rootactivity", "/rs/users/personal-activities/personal-root-activities/" + activity.getId());
            context.addLinkConf(ActivityResource.class, link);
        }));

        selectLink.put(Represent.SUBACTIVITY, ((activity, context) -> {
            Link link = Link.createLink("is-a-subactivity", "/rs/users/personal-activities/personal-root-activities/personal-sub-activities/" + activity.getId());
            context.addLinkConf(ActivityResource.class, link);
        }));

        selectLink.put(Represent.NO_REPRESENT, ((task, context) -> {}));
    }


    public ResourceItem build(Activity activity) {
        ActivityResource resource = buildItem(activity);

        return new ResourceItem(resource, request.getRequestURI());
    }

    public ResourceItems build(Iterable<Activity> activities) {

        List<ActivityResource> resources = Lists.newArrayList(activities).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, request.getRequestURI());
        ResourceHolder.setInfoPageable(activities, resourceItems);
        return  resourceItems;
    }

    private ActivityResource buildItem(Activity activity) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        selectLink.get(activity.getRepresent()).select(activity, context);
        context.addLinkConf(ActivityResource.class, Link.createLink("has-checklists", URI_CHECKLISTS.replace("{activityId}", activity.getId().toString())));

        ActivityResource resource = new ActivityResource();

        ConverterToResource.convert(activity, resource, context);
        return resource;
    }

    private interface SelectLink {

        void select(Activity activity, ContextArguments context);
    }
}
