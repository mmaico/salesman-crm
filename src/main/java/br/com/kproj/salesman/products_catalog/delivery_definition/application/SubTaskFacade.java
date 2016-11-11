package br.com.kproj.salesman.products_catalog.delivery_definition.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Subtask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.SubtaskToRootTask;

import java.util.Collection;
import java.util.Optional;

public interface SubTaskFacade extends ModelFacade<Subtask> {

    Collection<Subtask> findAll(RootTask rootTask);

    void delete(Subtask task);

    Optional<Subtask> register(SubtaskToRootTask subtaskToRootTask);
}
