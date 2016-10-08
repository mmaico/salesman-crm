package br.com.kproj.salesman.infrastructure.repository;



import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericCustomRepository<T> {

    Page<T> findAll(EntityPath<T> entity, Predicate predicate, Pageable page, OrderSpecifier<?>... orders);
}
