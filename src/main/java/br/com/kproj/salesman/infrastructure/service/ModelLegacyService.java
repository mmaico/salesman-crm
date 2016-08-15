package br.com.kproj.salesman.infrastructure.service;


import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ModelLegacyService<T> {

    T save(T entity, DomainBusinessRulesLegacy... checkRules);

    Iterable<T> findAll(Pageable pager);
    
    Optional<T> getOne(Long id);
}
