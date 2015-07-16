package br.com.kproj.salesman.infrastructure.repository;


import com.mysema.query.types.EntityPath;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericCustomRepository<T> {

    Page<T> findAll(EntityPath<T> entity, Predicate predicate, Pageable page, OrderSpecifier<?>... orders);
}
