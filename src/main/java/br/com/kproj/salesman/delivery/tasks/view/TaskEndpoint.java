package br.com.kproj.salesman.delivery.tasks.view;


import br.com.kproj.salesman.delivery.tasks.application.TaskFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskBuilder;
import br.com.kproj.salesman.delivery.tasks.view.support.builders.TaskResourceBuilder;
import br.com.kproj.salesman.delivery.tasks.view.support.resources.TaskResource;
import br.com.kproj.salesman.delivery.tasks.view.support.updates.TaskUpdateFields;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("taskEndpointDeliveryModule")
public class TaskEndpoint {

    private TaskFacade service;

    private TaskResourceBuilder builder;

    private TaskUpdateFields updateFields;

    @Autowired
    public TaskEndpoint(TaskFacade service, TaskResourceBuilder builder, TaskUpdateFields updateFields) {
        this.service = service;
        this.builder = builder;
        this.updateFields = updateFields;
    }

    @RequestMapping(value = "/rs/deliveries/{deliveryId}/tasks", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PathVariable Long deliveryId, @PageableDefault(size = 100) Pageable pageable) {
        Delivery delviery = new Delivery(deliveryId);

        Iterable<Task> rootTasks = service.findAll(delviery, pageable);

        return builder.build(rootTasks);
    }

    @RequestMapping(value = "/rs/deliveries/tasks/{taskId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long taskId) {

        Optional<Task> taskOptional = service.getOne(taskId);
        Task task = taskOptional.orElseThrow(NotFoundException::new);

        return builder.build(task);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/deliveries/{deliveryId}/tasks", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long deliveryId, @RequestBody TaskResource resource) {

        Task task = TaskBuilder.createTask()
                .withTitle(resource.getTitle())
                .withDescription(resource.getDescription())
                .withDeadline(resource.getDeadline())
                .withDelivery(deliveryId).build();

        Optional<Task> taskCreated = service.register(task);

        return builder.build(taskCreated.get());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/deliveries/tasks/{taskId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResourceItem update(@PathVariable Long taskId, @RequestBody TaskResource resource) {

        Task task = TaskBuilder.createTask(taskId)
                .withTitle(resource.getTitle())
                .withDescription(resource.getDescription())
                .withDeadline(resource.getDeadline())
                .withStatus(resource.getStatus())
                .build();

        updateFields.addFieldsToUpdate(task);
        Optional<Task> taskCreated = service.update(task);

        return builder.build(taskCreated.get());
    }


}
