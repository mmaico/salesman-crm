package br.com.kproj.salesman.assistants.calendar.calendar.view;



import br.com.kproj.salesman.assistants.calendar.calendar.application.CalendarFacade;
import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.user.User;
import br.com.kproj.salesman.assistants.calendar.calendar.view.support.builder.CalendarResourceBuilder;
import br.com.kproj.salesman.assistants.calendar.calendar.view.support.resource.CalendarResource;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("calendarEndpointCalendarModule")
public class CalendarEndpoint {

    private CalendarFacade service;

    private CalendarResourceBuilder builder;


    @Autowired
    public CalendarEndpoint(CalendarFacade service, CalendarResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }


    @RequestMapping(value = "/rs/users/calendars/{calendarId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long calendarId) {

        Optional<Calendar> calendarFound = service.getOne(calendarId);
        Calendar calendar = calendarFound.orElseThrow(NotFoundException::new);

        return builder.build(calendar);
    }

    //TODO:  fazer a correcao da url para criar usando /rs/users/1/calendars
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/users/calendars", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@RequestBody CalendarResource resource) {
        User user = new User(resource.getOwnerId());

        Optional<Calendar> calendarSaved = service.registerFor(user);

        return builder.build(calendarSaved.get());
    }

}
