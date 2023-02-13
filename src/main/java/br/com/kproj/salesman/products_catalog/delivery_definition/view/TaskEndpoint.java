package br.com.kproj.salesman.products_catalog.delivery_definition.view;


import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.delivery_definition.application.TaskFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.Region;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.view.support.builders.TaskResourceBuilder;
import br.com.kproj.salesman.products_catalog.delivery_definition.view.support.resources.TaskResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

import static br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskBuilder.createTask;
import static br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskToSaleable.createTaskToSaleable;


@RestController("taskEndpoinDefinitionModule")
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

    @RequestMapping(value = "/rs/saleables/{saleableId}/task-definitions", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PathVariable Long saleableId) {
        Saleable saleable = new Saleable(saleableId);

        Collection<Task> rootTasks = service.findAll(saleable);

        return builder.build(rootTasks, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/saleables/{saleableId}/task-definitions", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long saleableId, @RequestBody TaskResource resource) {

        Task task = createTask()
                .withDescription(resource.getDescription())
                .withQuantity(resource.getQuantityDaysToFinish())
                .withTitle(resource.getTitle())
                .withRegion(new Region(resource.getRegionId()))
                .build();

        Optional<Task> taskCreated = service.register(createTaskToSaleable(saleableId, task));

        return builder.build(taskCreated.get(), request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/task-definitions/{taskId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getOne(@PathVariable Long taskId) {

        Optional<Task> taskFound = service.getOne(taskId);

        Task task = taskFound.orElseThrow(() -> new NotFoundException());

        return builder.build(task, request.getRequestURI());
    }


}
