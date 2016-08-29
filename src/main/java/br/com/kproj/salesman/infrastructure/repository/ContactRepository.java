package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends BaseRepositoryLegacy<ContactEntity, Long> {


    @Query("SELECT c FROM ContactEntity AS c")
    List<ContactEntity> findByPerson(@Param("person") Person person);
}
