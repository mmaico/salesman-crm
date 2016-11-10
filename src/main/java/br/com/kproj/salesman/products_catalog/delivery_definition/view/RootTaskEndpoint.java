package br.com.kproj.salesman.products_catalog.delivery_definition.view;



import br.com.kproj.salesman.products_catalog.delivery_definition.application.RootTaskFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController("rootTaskEndpoinDefinitionModule")
public class RootTaskEndpoint {

    @Autowired
    private RootTaskFacade service;


    @RequestMapping(value = "/rs/saleables/{saleableId}/task-definitions", method = RequestMethod.GET)
    public @ResponseBody Collection<RootTask> getRootTasksBy(@PathVariable Long saleableId) {
        Saleable saleable = new Saleable(saleableId);

        Collection<RootTask> rootTasks = service.findAll(saleable);

        return rootTasks;
    }



}
