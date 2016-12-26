package br.com.kproj.salesman.assistants.calendar.view;


import br.com.kproj.salesman.assistants.calendar.application.ActivityContactFacade;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.ActivityContact;
import br.com.kproj.salesman.assistants.calendar.domain.model.relations.Contact;
import br.com.kproj.salesman.assistants.calendar.view.support.builder.ActivityContactResourceBuilder;
import br.com.kproj.salesman.assistants.calendar.view.support.resource.specialization.ActivityContactResource;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("activityCalendarContactEndpointCalendarModule")
public class ActivityContactEndpoint {

    private ActivityContactFacade service;

    private ActivityContactResourceBuilder builder;


    @Autowired
    public ActivityContactEndpoint(ActivityContactFacade service, ActivityContactResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/users/calendars/calendar-activities/calendar-activities-contacts/{activityId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long activityId) {

        Optional<ActivityContact> result = service.getOne(activityId);
        ActivityContact activity = result.orElseThrow(NotFoundException::new);

        return builder.build(activity);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/users/calendars/calendar-activities/{activityId}/calendar-activities-contacts", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long activityId, @RequestBody ActivityContactResource resource) {

        ActivityContact activityContact = new ActivityContact();
        activityContact.setId(activityId);
        activityContact.setContact(new Contact(resource.getContactId()));

        Optional<ActivityContact> activityRegistered = service.makeSpecialization(activityContact);

        return builder.build(activityRegistered.get());
    }

}
