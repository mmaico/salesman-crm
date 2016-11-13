package br.com.kproj.salesman.products_catalog.delivery_definition.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks.Subtask;
import br.com.kproj.salesman.products_catalog.delivery_definition.view.support.resources.SubTaskResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.apiconverter.configs.LinkRemoveConfig;
import br.com.uol.rest.apiconverter.resources.Link;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class SubtaskResourceBuilder {


    public ResourceItem build(Subtask rootTask, String uri) {
        SubTaskResource resource = buildItem(rootTask);

        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Collection<Subtask> subtasks, String uri) {
        List<SubTaskResource> resources = subtasks.stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, uri);
    }

    public SubTaskResource buildItem(Subtask task) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);
        Link link = Link.createLink("child-of", "/rs/saleables/task-definitions/root-task-definitions/" + task.getParent().getId());

        context.addLinkConf(SubTaskResource.class, link);
        context.addLinkConf(SubTaskResource.class, LinkRemoveConfig.createLinkRemoveConfig("child-of"));

        SubTaskResource resource = new SubTaskResource();

        ConverterToResource.convert(task, resource, context);
        return resource;
    }

}
