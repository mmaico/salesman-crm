package br.com.kproj.salesman.assistants.calendar.application.impl;


import br.com.kproj.salesman.assistants.calendar.application.ActivityFacade;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendarValidator;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityRepository;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.assistants.calendar.domain.model.user.User.user;

@Service
public class ActivityServiceImpl extends BaseModelServiceImpl<Activity> implements ActivityFacade {

    private ActivityRepository repository;
    private ActivityInCalendarValidator rules;

    @Autowired
    public ActivityServiceImpl(ActivityRepository repository, ActivityInCalendarValidator rules) {
        this.repository = repository;
        this.rules = rules;
    }

    @Override
    public Optional<Activity> register(ActivityInCalendar params) {
        rules.checkRules(params);

        return user().addAn(params.getActivity()).in(params.getCalendar());
    }

    @Override
    public Iterable<Activity> findAll(Calendar calendar) {
        return null;
    }

    @Override
    public BaseRepository<Activity, Long> getRepository() {
        return repository;
    }


}
