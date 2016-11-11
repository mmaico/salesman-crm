package br.com.kproj.salesman.products_catalog.delivery_definition.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Represent;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
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
public class TaskResourceBuilder {

    private static Map<Represent, SelectLink> selectLink = new HashMap<>();

    static {
        selectLink.put(Represent.ROOTTASK, ((task, context) -> {
            Link link = Link.createLink("is-a-roottask", "/rs/saleables/task-definitions/root-task-definitions/" + task.getId());
            Link linkSubtasks = Link.createLink("children", "/rs/saleables/task-definitions/root-task-definitions/" + task.getId() +"/subtask-definitions");

            context.addLinkConf(TaskResource.class, link);
            context.addLinkConf(TaskResource.class, linkSubtasks);
        }));

        selectLink.put(Represent.SUBTASK, ((task, context) -> {
            Link link = Link.createLink("is-a-subtask", "/rs/saleables/task-definitions/root-task-definitions/subtask-definitions/" + task.getId());
            context.addLinkConf(TaskResource.class, link);
        }));

        selectLink.put(Represent.NO_REPRESENT, ((task, context) -> {}));
    }


    public ResourceItem build(Task saleableUnit, String uri) {
        TaskResource resource = buildItem(saleableUnit);

        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Collection<Task> saleables, String uri) {
        List<TaskResource> resources = saleables.stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, uri);
    }

    public TaskResource buildItem(Task task) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        selectLink.get(task.getRepresent()).select(task, context);

        TaskResource resource = new TaskResource();

        ConverterToResource.convert(task, resource, context);
        return resource;
    }

    private interface SelectLink {

        void select(Task task, ContextArguments context);
    }
}
