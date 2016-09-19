package br.com.kproj.salesman.assistants.activities2.application;


import br.com.kproj.salesman.assistants.activities2.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities2.domain.model.checklist.Checklist;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Collection;

public interface ChecklistFacade extends ModelFacade<Checklist> {

    void completed(Checklist checklist);

    Collection<Checklist> findAll(Activity activity);
}
