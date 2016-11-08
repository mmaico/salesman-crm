package br.com.kproj.salesman.products_catalog.delivery_definition.application;


import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.Region;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTaskToSaleable;

import java.util.Collection;
import java.util.Optional;

public interface RootTaskFacade {

    Collection<RootTask> findAll(Saleable saleable);

    Collection<RootTask> findAll(Saleable saleable, Region region);

    void delete(RootTask task);

    Optional<RootTask> register(RootTaskToSaleable taskToSaleable);
}
