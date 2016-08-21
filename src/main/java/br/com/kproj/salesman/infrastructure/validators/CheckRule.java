package br.com.kproj.salesman.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

@FunctionalInterface
public interface CheckRule<T extends ModelIdentifiable> {

    Boolean check(T t);
}
