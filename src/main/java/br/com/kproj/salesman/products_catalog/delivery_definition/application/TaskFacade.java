package br.com.kproj.salesman.products_catalog.delivery_definition.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskToSaleable;

import java.util.Collection;
import java.util.Optional;

public interface TaskFacade extends ModelFacade<Task> {

    Collection<Task> findAll(Saleable saleable);

    Optional<Task> register(TaskToSaleable taskToSaleable);

    Optional<Task> update(Task task);

}
