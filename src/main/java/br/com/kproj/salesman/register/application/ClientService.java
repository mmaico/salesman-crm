package br.com.kproj.salesman.register.application;


import br.com.kproj.salesman.infrastructure.entity.person.Person;

public interface ClientService extends ModelService<Person> {

    Person register(Person client);
}
