package br.com.kproj.salesman.assistants.calendar.view;


import br.com.kproj.salesman.assistants.calendar.application.ActivityFacade;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityBuilder;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.view.support.builder.ActivityResourceBuilder;
import br.com.kproj.salesman.assistants.calendar.view.support.resource.ActivityResource;
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


@RestController("activityCalendarEndpointCalendarModule")
public class ActivityEndpoint {

    private ActivityFacade service;

    private ActivityResourceBuilder builder;


    @Autowired
    public ActivityEndpoint(ActivityFacade service, ActivityResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }


    @RequestMapping(value = "/rs/users/calendars/{calendarId}/calendar-activities", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems findAll(@PathVariable Long calendarId,
                          @RequestParam(value="filter", required=false) String filter,
                          @PageableDefault(size = 400) Pageable pageable) {

        FilterAggregator filters = FilterAggregator.build().generateFilters(filter);
        Calendar calendar = new Calendar(calendarId);

        Iterable<Activity> result = service.findAll(calendar, filters, pageable);

        if (Iterables.isEmpty(result)) {
            throw new NotFoundException("calendar.activities.not.found");
        }

        return builder.build(result);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/users/calendars/{calendarId}/calendar-activities", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long calendarId, @RequestBody ActivityResource resource) {

        Activity activity = createActivity()
                .withAllDay(resource.getAllDay())
                .withDescription(resource.getDescription())
                .withTitle(resource.getTitle())
                .withStart(resource.getStart())
                .withEnd(resource.getEnd())
                .withLocation(resource.getLocation()).build();

        ActivityInCalendar activityInCalendar = ActivityInCalendar.create(activity, new Calendar(calendarId));

        Optional<Activity> activityRegistered = service.register(activityInCalendar);

        return builder.build(activityRegistered.get());
    }

}
