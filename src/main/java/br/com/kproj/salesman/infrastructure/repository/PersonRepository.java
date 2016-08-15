package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.repository.custom.PersonRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends BaseRepositoryLegacy<Person, Long>, PersonRepositoryCustom {


    @Query("SELECT p FROM Person AS p WHERE p.id = :id")
    Optional<Person> getOne(@Param("id")Long id);

}
