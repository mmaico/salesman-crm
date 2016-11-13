package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;

import java.util.Optional;

public interface RootTaskRepository extends BaseRepository<RootTask, Long> {

    void delete(RootTask task);

    Optional<RootTask> add(RootTask task, Saleable saleable);

}
