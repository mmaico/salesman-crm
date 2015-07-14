package br.com.kproj.salesman.register.application;


import org.springframework.data.domain.Pageable;

public interface ModelService<T> {

    T save(T entity);

    Iterable<T> findAll(Pageable pager);
}
