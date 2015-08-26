package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.repository.custom.PersonRepositoryCustom;

public interface ContactRepository extends BaseRepository<Contact, Long>, PersonRepositoryCustom {


}
