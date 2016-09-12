package br.com.kproj.salesman.delivery2.tasks_template.application;


import br.com.kproj.salesman.delivery2.tasks_template.model.checklist.Checklist;
import br.com.kproj.salesman.delivery2.tasks_template.model.tasks.Task;

import java.util.Collection;

public interface ChecklistFacade {

    Collection<Checklist> findAll(Task task);

    void delete(Checklist checklist);
}
