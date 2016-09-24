package br.com.kproj.salesman.assistants.calendar.application.impl;


import br.com.kproj.salesman.assistants.calendar.application.CalendarFacade;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendarValidator;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.CalendarRepository;
import br.com.kproj.salesman.assistants.calendar.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalendarServiceImpl extends BaseModelServiceImpl<Calendar> implements CalendarFacade {

    private CalendarRepository repository;
    private ActivityInCalendarValidator rules;

    @Autowired
    public CalendarServiceImpl(CalendarRepository repository, ActivityInCalendarValidator rules) {
        this.repository = repository;
        this.rules = rules;
    }

    @Override
    public Optional<Calendar> registerFor(User user) {
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
