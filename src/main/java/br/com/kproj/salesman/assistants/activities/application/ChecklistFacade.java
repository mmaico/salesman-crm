package br.com.kproj.salesman.assistants.activities.application;


import br.com.kproj.salesman.assistants.activities.domain.model.checklist.AddChecklistInActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Collection;
import java.util.Optional;

public interface ChecklistFacade extends ModelFacade<Checklist> {

    Checklist update(Checklist checklist);

    Collection<Checklist> findAll(Activity activity);

    Optional<Checklist> register(AddChecklistInActivity addChecklistInActivity);
}
