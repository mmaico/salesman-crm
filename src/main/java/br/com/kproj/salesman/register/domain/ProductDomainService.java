package br.com.kproj.salesman.register.domain;

import br.com.kproj.salesman.infrastructure.entity.Product;
import br.com.kproj.salesman.infrastructure.entity.person.Person;

public interface ProductDomainService {

	void verifyPreconditionToSave(Product product);
}
