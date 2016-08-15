package br.com.kproj.salesman.infrastructure.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Optional;


public interface BaseRepository<T, ID extends Serializable> {

    Page<T> findAll(Pageable page);

    Optional<T> findOne(ID id);

    Optional<T> save(T entity);
}
