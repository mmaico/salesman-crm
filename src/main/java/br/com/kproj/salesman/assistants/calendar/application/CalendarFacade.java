package br.com.kproj.salesman.assistants.calendar.application;


import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface CalendarFacade extends ModelFacade<Calendar> {

    Optional<Calendar> registerFor(User user);


}
