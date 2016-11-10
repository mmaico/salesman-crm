package br.com.kproj.salesman.products_catalog.delivery_definition.view;



import br.com.kproj.salesman.infrastructure.http.response.handler.annotation.ResourceWrapper;
import br.com.kproj.salesman.products_catalog.delivery_definition.application.RootTaskFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController("rootTaskEndpoinDefinitionModule")
public class TaskEndpoint {

    @Autowired
    private RootTaskFacade service;


    @ResourceWrapper
    @RequestMapping(value = "/rs/saleables/{saleableId}/task-definitions", method = RequestMethod.GET)
    public @ResponseBody Collection<RootTask> getRootTasksBy(@PathVariable Long saleableId) {
        Saleable saleable = new Saleable(saleableId);

        Collection<RootTask> rootTasks = service.findAll(saleable);

        return rootTasks;
    }



}
