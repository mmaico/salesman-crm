package br.com.kproj.salesman.assistants.calendar.activities.specialization.view;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.application.ActivityNegotiationFacade;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityNegotiation;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.builder.ActivityNegotiationResourceBuilder;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.resource.ActivityNegotiationResource;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("activityCalendarNegotiationEndpointCalendarModule")
public class ActivityNegotiationEndpoint {

    private ActivityNegotiationFacade service;

    private ActivityNegotiationResourceBuilder builder;


    @Autowired
    public ActivityNegotiationEndpoint(ActivityNegotiationFacade service, ActivityNegotiationResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/users/calendars/calendar-activities/calendar-activities-negotiations/{activityId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long activityId) {

        Optional<ActivityNegotiation> result = service.getOne(activityId);
        ActivityNegotiation activity = result.orElseThrow(NotFoundException::new);

        return builder.build(activity);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/users/calendars/calendar-activities/{activityId}/calendar-activities-negotiations", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long activityId, @RequestBody ActivityNegotiationResource resource) {

        ActivityNegotiation activityNegotiation = new ActivityNegotiation();
        activityNegotiation.setId(activityId);
        activityNegotiation.setNegotiation(new Negotiation(resource.getNegotiationId()));

        Optional<ActivityNegotiation> activityRegistered = service.makeSpecialization(activityNegotiation);

        return builder.build(activityRegistered.get());
    }

}
