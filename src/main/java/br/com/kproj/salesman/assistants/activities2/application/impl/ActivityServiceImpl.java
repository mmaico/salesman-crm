package br.com.kproj.salesman.assistants.activities2.application.impl;

import br.com.kproj.salesman.assistants.activities2.application.ActivityFacade;
import br.com.kproj.salesman.assistants.activities2.application.validators.ActivityBusinessRules;
import br.com.kproj.salesman.assistants.activities2.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities2.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.assistants.activities2.domain.model.personal.ActivityValidator;
import br.com.kproj.salesman.assistants.activities2.domain.model.personal.SubActivity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityServiceImpl extends BaseModelServiceImpl<Activity> implements ActivityFacade {

    @Autowired
    private ActivityRepository repository;

    @Autowired
    private ActivityValidator validator;

    @Autowired
    private ActivityBusinessRules activityRules;


    @Override
    public Optional<Activity> register(Activity activity) {
        activityRules.checkRules(activity);

        return null;
    }

    @Override
    public Optional<SubActivity> register(SubActivity subActivity) {
        return null;
    }

    @Override
    public BaseRepository<Activity, Long> getRepository() {
        return repository;
    }
}
