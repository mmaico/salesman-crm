package br.com.kproj.salesman.assistants.calendar.domain.service;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;

import java.util.Optional;

@FunctionalInterface
public interface ActivityInCalendarService {

    Optional<Activity> in(Calendar calendar);
}
