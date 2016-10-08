package br.com.kproj.salesman.infrastructure.repository.custom;


import br.com.kproj.salesman.infrastructure.entity.person.Person;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonRepositoryCustom {

    Page<Person> findAll(Predicate predicate, Pageable page, OrderSpecifier<?>... orders);
}
