package br.com.kproj.salesman.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;

@FunctionalInterface
public interface CheckParamsRule<T extends Identifiable, Y extends Identifiable> {

    Boolean check(T t, Y y);
}
