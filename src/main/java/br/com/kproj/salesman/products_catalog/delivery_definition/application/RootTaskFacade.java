package br.com.kproj.salesman.products_catalog.delivery_definition.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RootTaskFacade extends ModelFacade<RootTask> {

    void delete(RootTask task);

    Optional<RootTask> register(RootTask task);

    Page<RootTask> findAll(Pageable page);
}
