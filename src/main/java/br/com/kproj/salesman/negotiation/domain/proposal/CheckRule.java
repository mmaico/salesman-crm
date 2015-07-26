package br.com.kproj.salesman.negotiation.domain.proposal;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;

@FunctionalInterface
public interface CheckRule<T extends Identifiable> {

    Boolean check(T t);
}
