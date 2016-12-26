package br.com.kproj.salesman.assistants.calendar.activities.activity.view;



import br.com.kproj.salesman.assistants.calendar.activities.activity.application.ActivityFacade;
import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.ActivityInCalendar;
import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.activities.activity.view.support.builder.ActivityResourceBuilder;
import br.com.kproj.salesman.assistants.calendar.activities.activity.view.support.resource.ActivityResource;
import br.com.kproj.salesman.assistants.calendar.activities.activity.view.support.update.ActivityUpdateFields;
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

import static br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.ActivityBuilder.createActivity;


@RestController("activityCalendarEndpointCalendarModule")
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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/users/calendars/calendar-activities/{activityId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResourceItem update(@PathVariable Long activityId, @RequestBody ActivityResource resource) {

        Activity activity = createActivity(activityId)
                .withAllDay(resource.getAllDay())
                .withDescription(resource.getDescription())
                .withTitle(resource.getTitle())
                .withStart(resource.getStart())
                .withEnd(resource.getEnd())
                .withLocation(resource.getLocation()).build();

        updateFields.addFieldsToUpdate(activity);
        Activity activityUpdated = service.update(activity);

        return builder.build(activityUpdated);
    }
}
