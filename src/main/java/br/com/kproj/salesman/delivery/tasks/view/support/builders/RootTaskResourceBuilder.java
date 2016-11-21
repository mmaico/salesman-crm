package br.com.kproj.salesman.delivery.tasks.view.support.builders;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.view.support.resources.RootTaskResource;
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

@Component("rootTaskResourceBuilderDeliveryTaskModule")
public class RootTaskResourceBuilder {


    private HttpServletRequest request;

    @Autowired
    public RootTaskResourceBuilder(HttpServletRequest request) {
        this.request = request;
    }

    public ResourceItem build(RootTask rootTask) {
        RootTaskResource resource = buildItem(rootTask);

        return new ResourceItem(resource, request.getRequestURI());
    }

    public ResourceItems build(Iterable<RootTask> rootTasks) {

        List<RootTaskResource> resources = Lists.newArrayList(rootTasks).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, request.getRequestURI());
    }

    public RootTaskResource buildItem(RootTask rootTask) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        RootTaskResource resource = new RootTaskResource();

        ConverterToResource.convert(rootTask, resource, context);
        return resource;
    }

}
