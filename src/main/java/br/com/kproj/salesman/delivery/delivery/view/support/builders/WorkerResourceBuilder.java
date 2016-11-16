package br.com.kproj.salesman.delivery.delivery.view.support.builders;


import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.delivery.delivery.view.support.resources.WorkerResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class WorkerResourceBuilder {


    public ResourceItem build(Worker worker, String uri) {
        WorkerResource resource = buildItem(worker);

        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Collection<Worker> workers, String uri) {
        List<WorkerResource> resources = workers.stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, uri);
    }

    public WorkerResource buildItem(Worker worker) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        WorkerResource resource = new WorkerResource();
        ConverterToResource.convert(worker, resource, context);

        return resource;
    }

}
