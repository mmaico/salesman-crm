package br.com.kproj.salesman.assistants.activities.application.impl;

import br.com.kproj.salesman.assistants.activities.application.ActivityFacade;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityValidator;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.SaveActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.assistants.activities.domain.model.user.Owner.owner;

@Service("activityServiceImplModuleActivities")
public class ActivityServiceImpl extends BaseModelServiceImpl<Activity> implements ActivityFacade {


    private ActivityRepository repository;
    private ActivityValidator activityRules;


    @Autowired
    public ActivityServiceImpl(ActivityRepository repository, ActivityValidator activityRules) {
        this.repository = repository;
        this.activityRules = activityRules;
    }

    @Override
    public Optional<Activity> register(SaveActivity activityToSave) {
        Owner owner = activityToSave.getOwner();
        Activity activity = activityToSave.getActivity();
        activityRules.checkRules(activity);

        return owner.save(activity);
    }

    @Override
    public Activity update(Activity activity) {

        return owner().update(activity);
    }

    @Override
    public Iterable<Activity> findAll(Owner owner, Pageable pageable) {
        return repository.findAll(owner, pageable);
    }


    @Override
    public BaseRepository<Activity, Long> getRepository() {
        return repository;
    }
}
