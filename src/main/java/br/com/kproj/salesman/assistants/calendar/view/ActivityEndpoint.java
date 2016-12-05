package br.com.kproj.salesman.assistants.calendar.view;


import br.com.kproj.salesman.assistants.calendar.application.ActivityFacade;
import br.com.kproj.salesman.assistants.calendar.view.support.build.ActivityResourceBuilder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    ResourceItem findOne(@PathVariable Long calendarId) {

//        Optional<Calendar> calendarFound = service.getOne(calendarId);
//        Calendar calendar = calendarFound.orElseThrow(NotFoundException::new);
//
//        return builder.build(calendar);
        return null;
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value = "/rs/users/calendars", method = RequestMethod.POST)
//    public @ResponseBody
//    ResourceItem create(@RequestBody CalendarResource resource) {
//        User user = new User(resource.getOwnerId());
//
//        Optional<Calendar> calendarSaved = service.registerFor(user);
//
//        return builder.build(calendarSaved.get());
//    }

}
