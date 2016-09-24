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

@Service
public class ChecklistServiceImpl extends BaseModelServiceImpl<Checklist> implements ChecklistFacade {

    @Autowired
    private ChecklistRepository repository;


    public Optional<Checklist> register(AddChecklistInActivity addChecklistInActivity) {
        Activity activity = addChecklistInActivity.getActivity();
        Optional<Checklist> checklist = activity.addChecklist(addChecklistInActivity.getChecklist());

        return checklist;
    }

    @Override
    public void completed(Checklist checklist) {
        checklist.makeCompleted();
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
