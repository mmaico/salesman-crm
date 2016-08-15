package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends BaseRepositoryLegacy<Address, Long> {

    @Query("SELECT a FROM Address AS a WHERE a.person = :person")
    List<Address> findByPerson(@Param("person") Person person);

}
