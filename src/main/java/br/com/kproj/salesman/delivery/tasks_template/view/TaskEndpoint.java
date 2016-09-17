package br.com.kproj.salesman.delivery.tasks_template.view;



import br.com.kproj.salesman.delivery.tasks_template.application.TaskFacade;
import br.com.kproj.salesman.delivery.tasks_template.model.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskEndpoint {

    @Autowired
    private TaskFacade service;


    @RequestMapping(value = "/rs/tasks/{taskId}", method = RequestMethod.GET)
    public @ResponseBody Task getTask(@PathVariable Long taskId) {

        return null;
    }



}
