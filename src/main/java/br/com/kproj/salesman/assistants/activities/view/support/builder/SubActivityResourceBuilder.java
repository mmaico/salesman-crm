package br.com.kproj.salesman.assistants.activities.view.support.builder;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivity;
import br.com.kproj.salesman.assistants.activities.view.support.resource.SubActivityResource;
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

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component("subactivityResourceBuilderActivitiesModule")
public class SubActivityResourceBuilder {


    private HttpServletRequest request;

    @Autowired
    public SubActivityResourceBuilder(HttpServletRequest request) {
        this.request = request;
    }

    public ResourceItem build(SubActivity subActivity) {
        SubActivityResource resource = buildItem(subActivity);

        return new ResourceItem(resource, request.getRequestURI());
    }

    public ResourceItems build(Iterable<SubActivity> subActivities) {

        List<SubActivityResource> resources = Lists.newArrayList(subActivities).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, request.getRequestURI());
        ResourceHolder.setInfoPageable(subActivities, resourceItems);
        return  resourceItems;
    }

    private SubActivityResource buildItem(SubActivity subActivity) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        SubActivityResource resource = new SubActivityResource();

        ConverterToResource.convert(subActivity, resource, context);
        return resource;
    }

}
