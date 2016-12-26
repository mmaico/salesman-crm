package br.com.kproj.salesman.assistants.calendar.activities.specialization.application.impl;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.application.ActivityContactFacade;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.application.validations.ActivityContactBusinessRules;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityContact;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityContactRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityContactServiceImpl extends BaseModelServiceImpl<ActivityContact> implements ActivityContactFacade {

    private ActivityContactRepository repository;
    private ActivityContactBusinessRules rules;

    @Autowired
    public ActivityContactServiceImpl(ActivityContactRepository repository, ActivityContactBusinessRules rules) {
        this.repository = repository;
        this.rules = rules;
    }

    @Override
    public BaseRepository<ActivityContact, Long> getRepository() {
        return repository;
    }

    @Override
    public Optional<ActivityContact> makeSpecialization(ActivityContact activityContact) {
        rules.checkRules(activityContact);

        return repository.makeSpecialization(activityContact);
    }
}
