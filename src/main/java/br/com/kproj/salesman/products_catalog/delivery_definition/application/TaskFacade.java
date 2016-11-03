package br.com.kproj.salesman.products_catalog.delivery_definition.application;


import br.com.kproj.salesman.products_catalog.delivery_definition.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.model.region.Region;
import br.com.kproj.salesman.products_catalog.delivery_definition.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.model.tasks.TaskToSaleable;

import java.util.Collection;
import java.util.Optional;

public interface TaskFacade {

    Collection<Task> findAll(Saleable saleable);

    Collection<Task> findAll(Saleable saleable, Region region);

    void delete(Task task);

    Optional<Task> register(TaskToSaleable taskToSaleable);
}
