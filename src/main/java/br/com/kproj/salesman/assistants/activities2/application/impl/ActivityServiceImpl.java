package br.com.kproj.salesman.assistants.activities2.application.impl;

import br.com.kproj.salesman.assistants.activities2.application.ActivityFacade;
import br.com.kproj.salesman.assistants.activities2.application.validators.ActivityBusinessRules;
import br.com.kproj.salesman.assistants.activities2.domain.model.personal.*;
import br.com.kproj.salesman.assistants.activities2.domain.model.user.Owner;
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
    private ActivityValidator activityRules;

    @Autowired
    private SubActivityValidator subactivityValidator;

    @Autowired
    private ChangeStatusValidator changeStatusRules;


    @Override
    public Optional<Activity> register(Activity activity) {
        activityRules.checkRules(activity);

        return repository.save(activity);
    }

    @Override
    public Optional<SubActivity> register(SubActivity subActivity) {
        subactivityValidator.checkRules(subActivity);

        return repository.save(subActivity);
    }

    @Override
    public void changeStatus(Owner owner, ChangeStatus changeStatus) {
        changeStatusRules.checkRules(owner, changeStatus);

        owner.changeStatus(changeStatus.getActivity()).toNewStatus(changeStatus.getNewStatus());
    }

    @Override
    public BaseRepository<Activity, Long> getRepository() {
        return repository;
    }
}
