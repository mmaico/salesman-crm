package br.com.kproj.salesman.products_catalog.delivery_definition.view;



import br.com.kproj.salesman.products_catalog.delivery_definition.application.TaskFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.model.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController("taskEndpointTemplateModule")
public class TaskEndpoint {

    @Autowired
    private TaskFacade service;


    @RequestMapping(value = "/rs/products/tasks/{taskId}", method = RequestMethod.GET)
    public @ResponseBody Task getTask(@PathVariable Long taskId) {

        return null;
    }



}
