package br.com.kproj.salesman.delivery.tasks.view;


import br.com.kproj.salesman.delivery.tasks.application.TaskFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskBuilder;
import br.com.kproj.salesman.delivery.tasks.view.support.builders.TaskResourceBuilder;
import br.com.kproj.salesman.delivery.tasks.view.support.resources.TaskResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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

    private HttpServletRequest request;

    @Autowired
    public TaskEndpoint(TaskFacade service, TaskResourceBuilder builder, HttpServletRequest request) {
        this.service = service;
        this.builder = builder;
        this.request = request;
    }

    @RequestMapping(value = "/rs/deliveries/{deliveryId}/tasks", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getTaskDefinitions(@PathVariable Long deliveryId, @PageableDefault(size = 100) Pageable pageable) {
        Delivery delviery = new Delivery(deliveryId);

        Iterable<Task> rootTasks = service.findAll(delviery, pageable);

        return builder.build(rootTasks, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/deliveries/tasks", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@RequestBody TaskResource resource) {

        Task task = TaskBuilder.createTask()
                .withTitle(resource.getTitle())
                .withDescription(resource.getDescription())
                .withDeadline(resource.getDeadline())
                .withDelivery(resource.getDeliveryId()).build();

        Optional<Task> taskCreated = service.register(task);

        return builder.build(taskCreated.get(), request.getRequestURI());
    }
//
//    @RequestMapping(value = "/rs/saleables/task-definitions/{taskId}", method = RequestMethod.GET)
//    public @ResponseBody
//    ResourceItem getOne(@PathVariable Long taskId) {
//
//        Optional<Task> taskFound = service.getOne(taskId);
//
//        Task task = taskFound.orElseThrow(() -> new NotFoundException());
//
//        return builder.build(task, request.getRequestURI());
//    }



}
