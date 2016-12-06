package br.com.kproj.salesman.assistants.calendar.domain.service;

import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;

@FunctionalInterface
public interface ActivityOfCalendarService {

    ActivityFilteredService of(Calendar calendar);
}
