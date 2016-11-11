package br.com.kproj.salesman.products_catalog.delivery_definition.view;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.delivery_definition.application.TaskFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.view.support.builders.TaskResourceBuilder;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


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
    ResourceItems getRootTasksBy(@PathVariable Long saleableId) {
        Saleable saleable = new Saleable(saleableId);

        Collection<Task> rootTasks = service.findAll(saleable);

        return builder.build(rootTasks, request.getRequestURI());
    }



}
