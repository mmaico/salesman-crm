package br.com.kproj.salesman.assistants.calendar.activities.specialization.application.impl;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.application.ActivitySaleableFacade;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.application.validations.ActivitySaleableBusinessRules;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivitySaleable;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivitySaleableRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivitySaleableServiceImpl extends BaseModelServiceImpl<ActivitySaleable> implements ActivitySaleableFacade {

    private ActivitySaleableRepository repository;
    private ActivitySaleableBusinessRules rules;

    @Autowired
    public ActivitySaleableServiceImpl(ActivitySaleableRepository repository, ActivitySaleableBusinessRules rules) {
        this.repository = repository;
        this.rules = rules;
    }

    @Override
    public BaseRepository<ActivitySaleable, Long> getRepository() {
        return repository;
    }

    @Override
    public Optional<ActivitySaleable> makeSpecialization(ActivitySaleable activitySaleable) {
        rules.checkRules(activitySaleable);

        return repository.makeSpecialization(activitySaleable);
    }
}
