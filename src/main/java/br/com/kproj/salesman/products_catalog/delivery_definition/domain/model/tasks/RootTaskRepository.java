package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.Region;

import java.util.Collection;
import java.util.Optional;

public interface RootTaskRepository extends BaseRepository<RootTask, Long> {

    Collection<RootTask> findAll(Saleable saleable);

    Collection<RootTask> findAll(Saleable saleable, Region region);

    void delete(RootTask task);

    Optional<RootTask> add(RootTask task, Saleable saleable);

}
