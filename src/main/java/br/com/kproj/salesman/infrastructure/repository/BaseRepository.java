package br.com.kproj.salesman.infrastructure.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean

public interface BaseRepository<T, Serializable> extends CrudRepository<T, Long>, QueryDslPredicateExecutor<T> {

    Page<T> findAll(Pageable page);
}
