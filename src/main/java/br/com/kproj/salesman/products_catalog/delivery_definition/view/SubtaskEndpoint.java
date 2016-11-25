package br.com.kproj.salesman.products_catalog.delivery_definition.view;


import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.delivery_definition.application.SubTaskFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks.Subtask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks.SubtaskBuilder;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks.SubtaskToRootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.view.support.builders.SubtaskResourceBuilder;
import br.com.kproj.salesman.products_catalog.delivery_definition.view.support.resources.SubTaskResource;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

import static br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks.SubtaskBuilder.createSubtask;


@RestController("subTaskEndpoinDefinitionModule")
public class SubtaskEndpoint {

    private SubTaskFacade service;

    private SubtaskResourceBuilder builder;

    private HttpServletRequest request;

    @Autowired
    public SubtaskEndpoint(SubtaskResourceBuilder builder, SubTaskFacade service, HttpServletRequest request) {
        this.builder = builder;
        this.service = service;
        this.request = request;
    }

    @RequestMapping(value = "/rs/saleables/task-definitions/root-task-definitions/{roottaskId}/subtask-definitions", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getSubtasks(@PathVariable Long roottaskId) {
        RootTask rootTask = new RootTask(roottaskId);

        Collection<Subtask> result = service.findAll(rootTask);

        if(Iterables.isEmpty(result)) {
            throw new NotFoundException();
        }

        return builder.build(result, request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/task-definitions/root-task-definitions/subtask-definitions/{subtaskId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long subtaskId) {

        Optional<Subtask> subtask = service.getOne(subtaskId);

        Subtask task = subtask.orElseThrow(() -> new NotFoundException());

        return builder.build(task, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/saleables/task-definitions/root-task-definitions/{rootTaskDefinitionId}/subtask-definitions", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long rootTaskDefinitionId, @RequestBody SubTaskResource resource) {

        Subtask subtask = createSubtask(resource.getTaskId()).build();

        SubtaskToRootTask toRootTask = SubtaskToRootTask.createSubtask(rootTaskDefinitionId, subtask);

        Optional<Subtask> subtaskCreated = service.register(toRootTask);

        Subtask task = subtaskCreated.orElseThrow(() -> new NotFoundException());

        return builder.build(task, request.getRequestURI());
    }



}
