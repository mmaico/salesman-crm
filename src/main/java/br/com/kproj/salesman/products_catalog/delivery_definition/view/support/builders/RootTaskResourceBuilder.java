package br.com.kproj.salesman.products_catalog.delivery_definition.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Represent;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.view.support.resources.RootTaskResource;
import br.com.kproj.salesman.products_catalog.delivery_definition.view.support.resources.TaskResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.apiconverter.resources.Link;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class RootTaskResourceBuilder {


    public ResourceItem build(RootTask rootTask, String uri) {
        RootTaskResource resource = buildItem(rootTask);

        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Collection<RootTask> saleables, String uri) {
        List<RootTaskResource> resources = saleables.stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, uri);
    }

    public RootTaskResource buildItem(RootTask task) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);
        Link linkSubtasks = Link.createLink("children", "/rs/saleables/task-definitions/root-task-definitions/" + task.getId() +"/subtask-definitions");

        context.addLinkConf(RootTaskResource.class, linkSubtasks);
        RootTaskResource resource = new RootTaskResource();

        ConverterToResource.convert(task, resource, context);
        return resource;
    }

}
