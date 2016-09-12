package br.com.kproj.salesman.delivery2.tasks.application;


import br.com.kproj.salesman.delivery2.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.Task;

import java.util.Collection;

public interface ChecklistFacade {

    Collection<Checklist> findAll(Task task);

    void completed(Checklist checklist);

}
