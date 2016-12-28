package br.com.kproj.salesman.assistants.activities.view;


import br.com.kproj.salesman.assistants.activities.application.ActivityFacade;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityBuilder;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.SaveActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.assistants.activities.view.support.builder.ActivityResourceBuilder;
import br.com.kproj.salesman.assistants.activities.view.support.resource.ActivityResource;
import br.com.kproj.salesman.assistants.activities.view.support.update.ActivityUpdateFields;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("activityEndpointActivitiesModule")
public class ActivityEndpoint {

    private ActivityFacade service;

    private ActivityResourceBuilder builder;

    private ActivityUpdateFields updateFields;

    @Autowired
    public ActivityEndpoint(ActivityFacade service, ActivityResourceBuilder builder, ActivityUpdateFields updateFields) {
        this.service = service;
        this.builder = builder;
        this.updateFields = updateFields;
    }

    @RequestMapping(value = "/rs/users/{ownerId}/personal-activities", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PathVariable Long ownerId, @PageableDefault(size = 100) Pageable pageable) {
        Owner owner = new Owner(ownerId);

        Iterable<Activity> activities = service.findAll(owner, pageable);
        if (Iterables.isEmpty(activities)) {
            throw new NotFoundException();
        }

        return builder.build(activities);
    }

    @RequestMapping(value = "/rs/users/personal-activities/{activityId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long activityId) {

        Optional<Activity> activityOptional = service.getOne(activityId);
        Activity activity = activityOptional.orElseThrow(NotFoundException::new);

        return builder.build(activity);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/users/{ownerId}/personal-activities", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long ownerId, @RequestBody ActivityResource resource) {

        Activity activity = ActivityBuilder.createActivity()
                .withTitle(resource.getTitle())
                .withDescription(resource.getDescription())
                .withDeadline(resource.getDeadline())
                .withOwner(ownerId).build();

        Optional<Activity> activityCreated = service.register(activity);

        return builder.build(activityCreated.get());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/users/personal-activities/{activityId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResourceItem update(@PathVariable Long activityId, @RequestBody ActivityResource resource) {

        Activity activity = ActivityBuilder.createActivity(activityId)
                .withTitle(resource.getTitle())
                .withDescription(resource.getDescription())
                .withDeadline(resource.getDeadline())
                .withStatus(resource.getStatus())
                .build();

        updateFields.addFieldsToUpdate(activity);
        Activity activityUpdated = service.update(activity);

        return builder.build(activityUpdated);
    }


}
