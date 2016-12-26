package br.com.kproj.salesman.assistants.calendar.activities.specialization.application.impl;


import br.com.kproj.salesman.assistants.calendar.application.ActivityContactFacade;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.ActivityContact;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.ActivityContactRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityContactServiceImpl extends BaseModelServiceImpl<ActivityContact> implements ActivityContactFacade {

    private ActivityContactRepository repository;


    @Autowired
    public ActivityContactServiceImpl(ActivityContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepository<ActivityContact, Long> getRepository() {
        return repository;
    }

    @Override
    public Optional<ActivityContact> makeSpecialization(ActivityContact activityContact) {

        return repository.makeSpecialization(activityContact);
    }
}
