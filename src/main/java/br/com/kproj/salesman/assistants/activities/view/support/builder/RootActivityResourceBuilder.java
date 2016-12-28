package br.com.kproj.salesman.assistants.activities.view.support.builder;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivity;
import br.com.kproj.salesman.assistants.activities.view.support.resource.RootActivityResource;
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
import java.util.List;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component("rootActivityResourceBuilderActivitiesModule")
public class RootActivityResourceBuilder {

    private static String URI_CHILDREN = "/rs/users/personal-activities/personal-root-activities/{rootActivityId}/personal-sub-activities";
    private HttpServletRequest request;

    @Autowired
    public RootActivityResourceBuilder(HttpServletRequest request) {
        this.request = request;
    }

    public ResourceItem build(RootActivity rootActivity) {
        RootActivityResource resource = buildItem(rootActivity);

        return new ResourceItem(resource, request.getRequestURI());
    }

    public ResourceItems build(Iterable<RootActivity> rootTasks) {

        List<RootActivityResource> resources = Lists.newArrayList(rootTasks).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, request.getRequestURI());
        ResourceHolder.setInfoPageable(rootTasks, resourceItems);
        return  resourceItems;
    }

    private RootActivityResource buildItem(RootActivity rootActivity) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);
        context.addLinkConf(RootActivityResource.class, Link.createLink("children", URI_CHILDREN.replace("{rootActivityId}", rootActivity.getId().toString())));

        RootActivityResource resource = new RootActivityResource();

        ConverterToResource.convert(rootActivity, resource, context);
        return resource;
    }

}
