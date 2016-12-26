package br.com.kproj.salesman.assistants.calendar.activities.activity.domain.service;


import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.calendar.Calendar;

@FunctionalInterface
public interface ActivityOfCalendarService {

    ActivityFilteredService of(Calendar calendar);
}
