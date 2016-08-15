package br.com.kproj.salesman.infrastructure.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepositoryLegacy<T, ID extends Serializable> extends CrudRepository<T, ID>, QueryDslPredicateExecutor<T> {

    Page<T> findAll(Pageable page);
}
