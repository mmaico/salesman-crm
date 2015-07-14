package br.com.kproj.salesman.infrastructure.repository;


import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, Serializable> extends CrudRepository<T, Long>, QueryDslPredicateExecutor<T> {
}
