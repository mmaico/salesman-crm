package br.com.kproj.salesman.assistants.calendar.application;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ActivityFacade extends ModelFacade<Activity> {



    Optional<Activity> register(ActivityInCalendar inCalendar);

    Iterable<Activity> findAll(Calendar calendar, FilterAggregator filters, Pageable pageable);

}
