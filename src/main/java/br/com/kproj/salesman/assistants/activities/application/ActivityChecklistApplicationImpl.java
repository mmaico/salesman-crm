package br.com.kproj.salesman.assistants.activities.application;

import br.com.kproj.salesman.assistants.activities.infrastructure.ActivityChecklistRepository;
import br.com.kproj.salesman.infrastructure.entity.activities.ActivityChecklist;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityChecklistApplicationImpl extends BaseModelServiceImpl<ActivityChecklist> implements ActivityChecklistApplication {

    @Autowired
    private ActivityChecklistRepository repository;


    @Override
    public ActivityChecklist register(ActivityChecklist checklist) {

        return super.save(checklist);
    }

    @Override
    public List<ActivityChecklist> findCheckListBy(PersonalActivity activity) {

        if (activity == null || activity.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findCheckListBy(activity);
    }

    @Override
    public void completed(ActivityChecklist checklist) {

        if (checklist.isNew()) return;

        Optional<ActivityChecklist> result = repository.getOne(checklist.getId());

        if(result.isPresent()) {
            result.get().setIsDone(Boolean.TRUE);
        }

    }

    public BaseRepository<ActivityChecklist, Long> getRepository() {
        return repository;
    }



}
