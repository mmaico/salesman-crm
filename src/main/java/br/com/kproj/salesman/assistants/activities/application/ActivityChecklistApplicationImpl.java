package br.com.kproj.salesman.assistants.activities.application;

import br.com.kproj.salesman.assistants.activities.infrastructure.ActivityChecklistRepository;
import br.com.kproj.salesman.infrastructure.entity.activities.ActivityChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityChecklistApplicationImpl extends BaseModelServiceLegacyImpl<ActivityChecklistEntity> implements ActivityChecklistApplication {

    @Autowired
    private ActivityChecklistRepository repository;


    @Override
    public ActivityChecklistEntity register(ActivityChecklistEntity checklist) {

        return super.save(checklist);
    }

    @Override
    public List<ActivityChecklistEntity> findCheckListBy(PersonalActivityEntity activity) {

        if (activity == null || activity.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findCheckListBy(activity);
    }

    @Override
    public void completed(ActivityChecklistEntity checklist) {

        if (checklist.isNew()) return;

        Optional<ActivityChecklistEntity> result = repository.getOne(checklist.getId());

        if(result.isPresent()) {
            result.get().setIsDone(Boolean.TRUE);
        }

    }

    public BaseRepositoryLegacy<ActivityChecklistEntity, Long> getRepository() {
        return repository;
    }



}
