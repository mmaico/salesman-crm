package br.com.kproj.salesman.assistants.activities.application;


import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Collection;

public interface ChecklistFacade extends ModelFacade<Checklist> {

    void completed(Owner owner, Checklist checklist);

    Collection<Checklist> findAll(Activity activity);
}
