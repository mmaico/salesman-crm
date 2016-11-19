package br.com.kproj.salesman.delivery.tasks.view.support.builders;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Represent;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.view.support.resources.TaskResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.apiconverter.resources.Link;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component("taskResourceBuilderDeliveryModule")
public class TaskResourceBuilder {

    private static Map<Represent, SelectLink> selectLink = new HashMap<>();

    static {
        selectLink.put(Represent.ROOTTASK, ((task, context) -> {
            Link link = Link.createLink("is-a-roottask", "/rs/deliveries/tasks/root-tasks/" + task.getId());
            context.addLinkConf(TaskResource.class, link);
        }));

        selectLink.put(Represent.SUBTASK, ((task, context) -> {
            Link link = Link.createLink("is-a-subtask", "/rs/deliveries/tasks/root-tasks/subtasks/" + task.getId());
            context.addLinkConf(TaskResource.class, link);
        }));

        selectLink.put(Represent.NO_REPRESENT, ((task, context) -> {}));
    }


    public ResourceItem build(Task saleableUnit, String uri) {
        TaskResource resource = buildItem(saleableUnit);

        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Iterable<Task> saleables, String uri) {

        List<TaskResource> resources = Lists.newArrayList(saleables).stream()
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
