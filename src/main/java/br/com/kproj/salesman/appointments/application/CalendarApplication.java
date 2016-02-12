package br.com.kproj.salesman.appointments.application;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface CalendarApplication extends ModelService<Calendar> {

    Calendar register(User user);
}
