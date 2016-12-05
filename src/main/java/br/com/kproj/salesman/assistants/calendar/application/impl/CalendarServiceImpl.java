package br.com.kproj.salesman.assistants.calendar.application.impl;


import br.com.kproj.salesman.assistants.calendar.application.CalendarFacade;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendarValidator;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.CalendarRepository;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.CalendarValidator;
import br.com.kproj.salesman.assistants.calendar.domain.model.user.User;
import br.com.kproj.salesman.assistants.calendar.domain.model.user.UserRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalendarServiceImpl extends BaseModelServiceImpl<Calendar> implements CalendarFacade {

    private CalendarRepository repository;
    private CalendarValidator validator;
    private ActivityInCalendarValidator rules;

    @Autowired
    public CalendarServiceImpl(CalendarRepository repository, ActivityInCalendarValidator rules, CalendarValidator validator) {
        this.repository = repository;
        this.rules = rules;
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
    public Optional<Activity> register(ActivityInCalendar params) {
        rules.checkRules(params);
        User user = params.getUser();

        return user.addAn(params.getActivity()).in(params.getCalendar());
    }

    @Override
    public BaseRepository<Calendar, Long> getRepository() {
        return repository;
    }


}
