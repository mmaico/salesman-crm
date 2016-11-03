package br.com.kproj.salesman.products_catalog.delivery_definition.application;


import br.com.kproj.salesman.products_catalog.delivery_definition.model.checklist.Checklist;
import br.com.kproj.salesman.products_catalog.delivery_definition.model.tasks.Task;

import java.util.Collection;

public interface ChecklistFacade {

    Collection<Checklist> findAll(Task task);

    void delete(Checklist checklist);
}
