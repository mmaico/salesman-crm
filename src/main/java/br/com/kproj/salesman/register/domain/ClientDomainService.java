package br.com.kproj.salesman.register.domain;

import br.com.kproj.salesman.infrastructure.entity.person.client.Client;

public interface ClientDomainService {

	void verifyPreconditionToSave(Client person);
}
