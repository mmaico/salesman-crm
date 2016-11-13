package br.com.kproj.salesman.products_catalog.delivery_definition.domain.service;

import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;

import java.util.Optional;

@FunctionalInterface
public interface ToSaleable {

    Optional<Task> to(Saleable saleable);
}
