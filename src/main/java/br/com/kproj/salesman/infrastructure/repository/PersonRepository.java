package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.repository.custom.PersonRepositoryCustom;
import org.springframework.data.repository.history.RevisionRepository;

public interface PersonRepository extends BaseRepository<Person, Long>, PersonRepositoryCustom {



}
