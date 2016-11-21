package br.com.kproj.salesman.delivery.tasks.application;


import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistForTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ChecklistFacade extends ModelFacade<Checklist> {

    Iterable<Checklist> findAll(Task task, Pageable pageable);

    Optional<Checklist> save(ChecklistForTask checklistForTask);

    Checklist update(Checklist checklist);



}
