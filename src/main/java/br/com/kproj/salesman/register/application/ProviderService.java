package br.com.kproj.salesman.register.application;


import br.com.kproj.salesman.infrastructure.entity.person.Person;

public interface ProviderService extends ModelService<Person> {

    Person register(Person client);
}
