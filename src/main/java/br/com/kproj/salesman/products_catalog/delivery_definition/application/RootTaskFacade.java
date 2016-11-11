package br.com.kproj.salesman.products_catalog.delivery_definition.application;


import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTaskToSaleable;

import java.util.Optional;

public interface RootTaskFacade {

    void delete(RootTask task);

    Optional<RootTask> register(RootTaskToSaleable taskToSaleable);
}
