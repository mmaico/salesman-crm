package br.com.kproj.salesman.assistants.activities.view;


import br.com.kproj.salesman.assistants.activities.application.ChecklistFacade;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.AddChecklistInActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.ChecklistBuilder;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityBuilder;
import br.com.kproj.salesman.assistants.activities.view.support.builder.ChecklistResourceBuilder;
import br.com.kproj.salesman.assistants.activities.view.support.resource.ChecklistResource;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.RequestBody.body;
import static br.com.kproj.salesman.infrastructure.model.ModelIdentifiable.When.when;


@RestController("checklistEndpointActivitiesModule")
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

    @RequestMapping(value = "/rs/users/personal-activities/{activityId}/activities-checklists", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PathVariable Long activityId) {
        Activity activity = ActivityBuilder.createActivity(activityId).build();

        Iterable<Checklist> checklists = service.findAll(activity);

        return builder.build(checklists);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/users/personal-activities/{activityId}/activities-checklists", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long activityId, @RequestBody ChecklistResource resource) {
        Activity activity = ActivityBuilder.createActivity(activityId).build();
        Checklist checklist = ChecklistBuilder.createChecklist()
                .withDone(resource.getDone())
                .withName(resource.getName()).build();

        Optional<Checklist> checklistCreated = service.register(new AddChecklistInActivity(checklist, activity));

        return builder.build(checklistCreated.get());
    }

    @RequestMapping(value = "/rs/users/personal-activities/activities-checklists/{checklistId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getOne(@PathVariable Long checklistId) {

        Optional<Checklist> checklistFound = service.getOne(checklistId);

        Checklist checklist = checklistFound.orElseThrow(NotFoundException::new);

        return builder.build(checklist);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/users/personal-activities/activities-checklists/{checklistId}", method = RequestMethod.PUT)
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
