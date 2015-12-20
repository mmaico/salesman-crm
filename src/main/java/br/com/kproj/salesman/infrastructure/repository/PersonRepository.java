package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.repository.custom.PersonRepositoryCustom;

public interface PersonRepository extends BaseRepository<Person, Long>, PersonRepositoryCustom {



}
