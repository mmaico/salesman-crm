package br.com.kproj.salesman.delivery.tasks.view;


import br.com.kproj.salesman.delivery.tasks.application.ChecklistFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistBuilder;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistForTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskBuilder;
import br.com.kproj.salesman.delivery.tasks.view.support.builders.ChecklistResourceBuilder;
import br.com.kproj.salesman.delivery.tasks.view.support.resources.ChecklistResource;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.RequestBody.body;
import static br.com.kproj.salesman.infrastructure.model.ModelIdentifiable.When.when;


@RestController("checklistEndpointDeliveryTaskModule")
public class ChecklistEndpoint {

    private ChecklistFacade service;

    private ChecklistResourceBuilder builder;

    private HttpServletRequest request;


    @Autowired
    public ChecklistEndpoint(ChecklistFacade service, ChecklistResourceBuilder builder, HttpServletRequest request) {
        this.service = service;
        this.builder = builder;
        this.request = request;
    }

    @RequestMapping(value = "/rs/deliveries/tasks/{taskId}/checklists", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems listChecklists(@PathVariable Long taskId, @PageableDefault(size = 1000) Pageable pageable) {
        Task task = TaskBuilder.createTask(taskId).build();

        Iterable<Checklist> checklists = service.findAll(task, pageable);

        return builder.build(checklists);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/deliveries/tasks/checklists", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@RequestBody ChecklistResource resource) {

        Checklist checklist = ChecklistBuilder.createChecklist()
                .withDone(resource.getDone())
                .withName(resource.getName()).build();

        ChecklistForTask checklistToTask = ChecklistForTask.createChecklistToTask(resource.getTaskId(), checklist);
        Optional<Checklist> checklistCreated = service.save(checklistToTask);

        return builder.build(checklistCreated.get());
    }

    @RequestMapping(value = "/rs/deliveries/tasks/checklists/{checklistId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getOne(@PathVariable Long checklistId) {

        Optional<Checklist> checklistFound = service.getOne(checklistId);

        Checklist checklist = checklistFound.orElseThrow(NotFoundException::new);

        return builder.build(checklist);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/deliveries/tasks/checklists/{checklistId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResourceItem update(@PathVariable Long checklistId, @RequestBody ChecklistResource resource) {

        Checklist checklist = ChecklistBuilder.createChecklist(checklistId)
                .withDone(resource.getDone())
                .withName(resource.getName()).build();

        checklist.addField("name", when(body(request).has("name")));
        checklist.addField("done", when(body(request).has("done")));

        Checklist checklistUpdated = service.update(checklist);

        return builder.build(checklistUpdated);
    }

}
