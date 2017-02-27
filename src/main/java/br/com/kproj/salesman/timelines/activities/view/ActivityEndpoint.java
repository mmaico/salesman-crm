package br.com.kproj.salesman.timelines.activities.view;


import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.timelines.activities.application.ActivityFacade;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.ActivityBuilder;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.NewActivityInTimeline;
import br.com.kproj.salesman.timelines.activities.domain.model.timeline.Timeline;
import br.com.kproj.salesman.timelines.activities.domain.model.user.User;
import br.com.kproj.salesman.timelines.activities.view.support.builders.ActivityResourceBuilder;
import br.com.kproj.salesman.timelines.activities.view.support.resources.ActivityResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


@RestController("activitiesEndpointActivitiesModule")
public class ActivityEndpoint {

    private ActivityFacade service;

    private ActivityResourceBuilder builder;


    @Autowired
    public ActivityEndpoint(ActivityFacade service, ActivityResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/timelines/{timelineId}/activities", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PathVariable Long timelineId, @PageableDefault(size = 100) Pageable pageable) {
        Timeline timeline = new Timeline(timelineId);

        Iterable<Activity> activities = service.findAll(timeline, pageable);

        return builder.build(activities);
    }

    @RequestMapping(value = "/rs/timelines/activities/{activityId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long activityId) {

        Optional<Activity> activityOptional = service.getOne(activityId);
        Activity activity = activityOptional.orElseThrow(NotFoundException::new);

        return builder.build(activity);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/timelines/{timelineId}/activities", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long timelineId, @RequestBody ActivityResource resource) {
        //pegar o usuario logado
        User user = new User(1l);

        Activity activity = ActivityBuilder.createActivity()
                .withCreationNow()
                .withTag(resource.getTag())
                .withDescription(resource.getDescription()).build();

        Timeline timeline = new Timeline(timelineId);

        NewActivityInTimeline newActivity = NewActivityInTimeline.newActivity(activity, timeline, user);

        Activity activityCreated = service.register(newActivity);

        return builder.build(activityCreated);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/timelines/activities/{activityId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResourceItem update(@PathVariable Long activityId, @RequestBody ActivityResource resource) {

        Activity activity = ActivityBuilder.createActivity(activityId)
                .withTag(resource.getTag())
                .withDescription(resource.getDescription()).build();

        activity.addFields("tag");
        activity.addFields("description");

        Activity activityUpdated = service.update(activity);

        return builder.build(activityUpdated);
    }


}
