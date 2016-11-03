package br.com.kproj.salesman.products_catalog.delivery_definition.model.tasks;


import br.com.kproj.salesman.products_catalog.delivery_definition.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.model.region.Region;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Collection;
import java.util.Optional;

public interface TaskRepository extends BaseRepository<Task, Long> {

    Collection<Task> findAll(Saleable saleable);

    Collection<Task> findAll(Saleable saleable, Region region);

    void delete(Task task);

    Optional<Task> add(Task task, Saleable saleable);

}
