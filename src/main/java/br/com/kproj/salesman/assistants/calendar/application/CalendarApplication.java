package br.com.kproj.salesman.assistants.calendar.application;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface CalendarApplication extends ModelLegacyService<Calendar> {

    Calendar register(User user);
}
