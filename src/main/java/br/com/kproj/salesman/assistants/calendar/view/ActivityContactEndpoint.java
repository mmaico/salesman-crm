package br.com.kproj.salesman.assistants.calendar.view;


import br.com.kproj.salesman.assistants.calendar.application.ActivityFacade;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.ActivityContact;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.view.support.builder.ActivityContactResourceBuilder;
import br.com.kproj.salesman.assistants.calendar.view.support.builder.ActivityResourceBuilder;
import br.com.kproj.salesman.assistants.calendar.view.support.resource.ActivityResource;
import br.com.kproj.salesman.assistants.calendar.view.support.resource.specialization.ActivityContactResource;
import br.com.kproj.salesman.assistants.calendar.view.support.update.ActivityUpdateFields;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityBuilder.createActivity;


@RestController("activityCalendarContactEndpointCalendarModule")
public class ActivityContactEndpoint {

    private ActivityFacade service;

    private ActivityContactResourceBuilder builder;


    @Autowired
    public ActivityContactEndpoint(ActivityFacade service, ActivityContactResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }


    @RequestMapping(value = "/rs/users/calendars/calendar-activities/calendar-activities-contacts/{activityId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long activityId) {

        Optional<Activity> result = service.getOne(activityId);
        Activity activity = result.orElseThrow(NotFoundException::new);

        return builder.build(activity);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/users/calendars/calendar-activities/{activityId}/calendar-activities-contacts", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long activityId) {
        ActivityContact activityContact = new ActivityContact();
        activityContact.setId(activityId);

        Optional<Activity> activityRegistered = service.register(activityInCalendar);

        return builder.build(activityRegistered.get());
    }

}
