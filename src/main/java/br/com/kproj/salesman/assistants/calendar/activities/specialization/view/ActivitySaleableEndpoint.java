package br.com.kproj.salesman.assistants.calendar.activities.specialization.view;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.application.ActivitySaleableFacade;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivitySaleable;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.saleable.Saleable;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.builder.ActivitySaleableResourceBuilder;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.resource.ActivitySaleableResource;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("activityCalendarSaleableEndpointCalendarModule")
public class ActivitySaleableEndpoint {

    private ActivitySaleableFacade service;

    private ActivitySaleableResourceBuilder builder;


    @Autowired
    public ActivitySaleableEndpoint(ActivitySaleableFacade service, ActivitySaleableResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/users/calendars/calendar-activities/calendar-activities-saleables/{activityId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long activityId) {

        Optional<ActivitySaleable> result = service.getOne(activityId);
        ActivitySaleable activity = result.orElseThrow(NotFoundException::new);

        return builder.build(activity);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/users/calendars/calendar-activities/{activityId}/calendar-activities-saleables", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long activityId, @RequestBody ActivitySaleableResource resource) {

        ActivitySaleable activitySaleable = new ActivitySaleable();
        activitySaleable.setId(activityId);
        activitySaleable.setSaleable(new Saleable(resource.getSaleableId()));

        Optional<ActivitySaleable> activityRegistered = service.makeSpecialization(activitySaleable);

        return builder.build(activityRegistered.get());
    }

}
