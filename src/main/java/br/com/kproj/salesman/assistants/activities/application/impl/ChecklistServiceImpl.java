package br.com.kproj.salesman.assistants.activities.application.impl;

import br.com.kproj.salesman.assistants.activities.application.ChecklistFacade;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.AddChecklistInActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static br.com.kproj.salesman.assistants.activities.domain.model.user.Owner.owner;

@Service("checklistFacadeActivitiesModule")
public class ChecklistServiceImpl extends BaseModelServiceImpl<Checklist> implements ChecklistFacade {

    @Autowired
    private ChecklistRepository repository;


    public Optional<Checklist> register(AddChecklistInActivity addChecklistInActivity) {
        Activity activity = addChecklistInActivity.getActivity();
        Checklist checklist = addChecklistInActivity.getChecklist();

        return owner().add(checklist).in(activity);
    }

    @Override
    public Checklist update(Checklist checklist) {
        return owner().update(checklist);
    }

    @Override
    public Collection<Checklist> findAll(Activity activity) {
        return repository.findAll(activity);
    }


    @Override
    public BaseRepository<Checklist, Long> getRepository() {
        return repository;
    }
}
