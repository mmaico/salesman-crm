package br.com.kproj.salesman.timelines.activities.application.impl;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.timelines.activities.application.ActivityFacade;
import br.com.kproj.salesman.timelines.activities.application.validators.descriptions.ActivityRulesDescription;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.ActivityRepository;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.ActivityValidator;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.NewActivityInTimeline;
import br.com.kproj.salesman.timelines.activities.domain.model.timeline.Timeline;
import br.com.kproj.salesman.timelines.activities.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.timelines.activities.application.validators.descriptions.ActivityRulesDescription.ignoreRules;
import static br.com.kproj.salesman.timelines.activities.application.validators.descriptions.ActivityRulesDescription.ruleValidUser;
import static br.com.kproj.salesman.timelines.activities.domain.model.user.User.user;

@Service("activityServiceImplTimelineActivitiesModule")
public class ActivityServiceImpl extends BaseModelServiceImpl<Activity> implements ActivityFacade {


    private ActivityRepository repository;

    private ActivityValidator validator;


    @Autowired
    public ActivityServiceImpl(ActivityRepository repository, ActivityValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Activity register(NewActivityInTimeline newActivity) {
        validator.checkRules(newActivity.getActivity());

        Activity activity = newActivity.getActivity();
        Timeline timeline = newActivity.getTimeline();
        User user = newActivity.getUser();

        return user.createNew(activity).in(timeline);
    }

    @Override
    public Activity update(Activity activity) {
        validator.checkRules(activity, ignoreRules(ruleValidUser()));

        return user().update(activity);
    }

    @Override
    public Iterable<Activity> findAll(Timeline timeline, Pageable pageable) {
        return repository.findAll(timeline, pageable);
    }

    @Override
    public BaseRepository<Activity, Long> getRepository() {
        return repository;
    }

}
