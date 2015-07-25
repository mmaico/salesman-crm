package br.com.kproj.salesman.register.application;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface ProviderService extends ModelService<Person> {

    Person register(Person client);
}
