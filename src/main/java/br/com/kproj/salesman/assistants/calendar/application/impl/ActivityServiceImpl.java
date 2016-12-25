package br.com.kproj.salesman.assistants.calendar.application.impl;


import br.com.kproj.salesman.assistants.calendar.application.ActivityFacade;
import br.com.kproj.salesman.assistants.calendar.application.validators.ActivityRulesDescription;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendarValidator;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityRepository;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.assistants.calendar.application.validators.ActivityRulesDescription.activityNotExists;
import static br.com.kproj.salesman.assistants.calendar.application.validators.ActivityRulesDescription.ignoreRules;
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
        Activity activity = params.getActivity();
        Calendar calendar = params.getCalendar();
        activity.setCalendar(calendar);

        rules.checkRules(activity, ignoreRules(activityNotExists()));

        return user().addAn(activity).in(calendar);
    }

    @Override
    public Iterable<Activity> findAll(Calendar calendar, FilterAggregator filters, Pageable pageable) {
        return user().getActivities().of(calendar).using(filters, pageable);
    }

    @Override
    public Activity update(Activity activity) {

        rules.checkRules(activity);

        return user().update(activity);
    }


    @Override
    public BaseRepository<Activity, Long> getRepository() {
        return repository;
    }


}
