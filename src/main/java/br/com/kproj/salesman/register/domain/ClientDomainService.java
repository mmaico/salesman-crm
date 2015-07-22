package br.com.kproj.salesman.register.domain;

import br.com.kproj.salesman.infrastructure.entity.person.Person;

public interface ClientDomainService {

	void verifyPreconditionToSave(Person person);
}
