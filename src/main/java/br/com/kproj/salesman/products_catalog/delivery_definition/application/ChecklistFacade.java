package br.com.kproj.salesman.products_catalog.delivery_definition.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.Checklist;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.ChecklistToTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;

import java.util.Collection;
import java.util.Optional;

public interface ChecklistFacade extends ModelFacade<Checklist> {

    Collection<Checklist> findAll(Task task);

    void delete(Checklist checklist);

    Optional<Checklist> register(ChecklistToTask task);
}
