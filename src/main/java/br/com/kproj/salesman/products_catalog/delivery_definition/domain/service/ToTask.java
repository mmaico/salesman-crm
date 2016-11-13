package br.com.kproj.salesman.products_catalog.delivery_definition.domain.service;

import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.Checklist;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;

import java.util.Optional;

@FunctionalInterface
public interface ToTask {

    Optional<Checklist> in(Task task);
}
