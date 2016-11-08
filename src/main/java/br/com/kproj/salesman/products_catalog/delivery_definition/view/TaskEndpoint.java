package br.com.kproj.salesman.products_catalog.delivery_definition.view;



import br.com.kproj.salesman.products_catalog.delivery_definition.application.RootTaskFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController("taskEndpointTemplateModule")
public class TaskEndpoint {

    @Autowired
    private RootTaskFacade service;


    @RequestMapping(value = "/rs/products/tasks/{taskId}", method = RequestMethod.GET)
    public @ResponseBody Task getTask(@PathVariable Long taskId) {

        return null;
    }



}
