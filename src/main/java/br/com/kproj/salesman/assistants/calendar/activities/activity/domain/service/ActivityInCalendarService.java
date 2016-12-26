package br.com.kproj.salesman.assistants.calendar.activities.activity.domain.service;



import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.calendar.Calendar;

import java.util.Optional;

@FunctionalInterface
public interface ActivityInCalendarService {

    Optional<Activity> in(Calendar calendar);
}
