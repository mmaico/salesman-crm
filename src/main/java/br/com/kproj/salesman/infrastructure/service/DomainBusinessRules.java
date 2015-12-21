package br.com.kproj.salesman.infrastructure.service;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

public interface DomainBusinessRules<T extends Identifiable> {

    public void checkBusinessRulesFor(T entity);

}
