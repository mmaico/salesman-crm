package br.com.kproj.salesman.products_catalog.delivery_definition.view;


import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.delivery_definition.application.RootTaskFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTaskBuilder;
import br.com.kproj.salesman.products_catalog.delivery_definition.view.support.builders.RootTaskResourceBuilder;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@RestController("rootTaskEndpoinDefinitionModule")
public class RootTaskEndpoint {

    private RootTaskFacade service;

    private RootTaskResourceBuilder builder;

    private HttpServletRequest request;

    @Autowired
    public RootTaskEndpoint (RootTaskResourceBuilder builder, RootTaskFacade service, HttpServletRequest request) {
        this.builder = builder;
        this.service = service;
        this.request = request;
    }
    //TODO ajustar para buscar todas as roots de um saleable
    @RequestMapping(value = "/rs/saleables/task-definitions/root-task-definitions", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getRootTasksBy() {

        Page<RootTask> result = service.findAll(Pager.build().withPageSize(10000));

        if(Iterables.isEmpty(result)) {
            throw new NotFoundException();
        }

        return builder.build(result.getContent(), request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/task-definitions/root-task-definitions/{rootTaskId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long rootTaskId) {

        Optional<RootTask> rootTask = service.getOne(rootTaskId);

        RootTask task = rootTask.orElseThrow(() -> new NotFoundException());

        return builder.build(task, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/saleables/task-definitions/{taskDefinitionId}/root-task-definitions", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long taskDefinitionId) {

        RootTask rootTask = RootTaskBuilder.createRootTask(taskDefinitionId).build();

        Optional<RootTask> rootTaskCreated = service.register(rootTask);

        return builder.build(rootTaskCreated.get(), request.getRequestURI());
    }

}
