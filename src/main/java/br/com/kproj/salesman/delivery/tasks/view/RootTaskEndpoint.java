package br.com.kproj.salesman.delivery.tasks.view;


import br.com.kproj.salesman.delivery.tasks.application.RootTaskFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTaskBuilder;
import br.com.kproj.salesman.delivery.tasks.view.support.builders.RootTaskResourceBuilder;
import br.com.kproj.salesman.delivery.tasks.view.support.resources.RootTaskResource;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("rootTaskEndpointDeliveryTaskModule")
public class RootTaskEndpoint {

    private RootTaskFacade service;

    private RootTaskResourceBuilder builder;


    @Autowired
    public RootTaskEndpoint (RootTaskResourceBuilder builder, RootTaskFacade service) {
        this.builder = builder;
        this.service = service;
    }

    @RequestMapping(value = "/rs/deliveries/{deliveryId}/tasks/root-tasks", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems listRootTasks(@PathVariable Long deliveryId, @PageableDefault(size = 1000) Pageable pageable) {

        Iterable<RootTask> rootTasks = service.findAll(deliveryId, pageable);

        if(Iterables.isEmpty(rootTasks)) {
            throw new NotFoundException();
        }

        return builder.build(rootTasks);
    }

    @RequestMapping(value = "/rs/deliveries/tasks/root-tasks/{rootTaskId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long rootTaskId) {

        Optional<RootTask> rootTask = service.getOne(rootTaskId);

        RootTask task = rootTask.orElseThrow(NotFoundException::new);

        return builder.build(task);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/deliveries/tasks/root-tasks", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem createSpecialization(@RequestBody RootTaskResource resource) {

        RootTask rootTask = RootTaskBuilder.createRootTask(resource.getTaskId()).build();

        Optional<RootTask> rootTaskCreated = service.register(rootTask);

        return builder.build(rootTaskCreated.get());
    }

}
