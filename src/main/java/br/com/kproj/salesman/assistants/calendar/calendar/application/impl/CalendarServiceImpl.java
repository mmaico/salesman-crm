package br.com.kproj.salesman.assistants.calendar.calendar.application.impl;



import br.com.kproj.salesman.assistants.calendar.calendar.application.CalendarFacade;
import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.calendar.CalendarRepository;
import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.calendar.CalendarValidator;
import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalendarServiceImpl extends BaseModelServiceImpl<Calendar> implements CalendarFacade {

    private CalendarRepository repository;
    private CalendarValidator validator;

    @Autowired
    public CalendarServiceImpl(CalendarRepository repository, CalendarValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Optional<Calendar> registerFor(User user) {
        Calendar calendar = new Calendar();
        calendar.setOwner(user);

        validator.checkRules(calendar);

        return repository.registerFor(user);
    }

    @Override
    public BaseRepository<Calendar, Long> getRepository() {
        return repository;
    }


}
