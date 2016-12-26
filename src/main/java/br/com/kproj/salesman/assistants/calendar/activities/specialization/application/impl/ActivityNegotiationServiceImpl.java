package br.com.kproj.salesman.assistants.calendar.activities.specialization.application.impl;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.application.ActivityNegotiationFacade;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.application.validations.ActivityNegotiationBusinessRules;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityNegotiation;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityNegotiationRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityNegotiationServiceImpl extends BaseModelServiceImpl<ActivityNegotiation> implements ActivityNegotiationFacade {

    private ActivityNegotiationRepository repository;
    private ActivityNegotiationBusinessRules rules;

    @Autowired
    public ActivityNegotiationServiceImpl(ActivityNegotiationRepository repository, ActivityNegotiationBusinessRules rules) {
        this.repository = repository;
        this.rules = rules;
    }

    @Override
    public BaseRepository<ActivityNegotiation, Long> getRepository() {
        return repository;
    }

    @Override
    public Optional<ActivityNegotiation> makeSpecialization(ActivityNegotiation activityNegotiation) {
        rules.checkRules(activityNegotiation);

        return repository.makeSpecialization(activityNegotiation);
    }
}
