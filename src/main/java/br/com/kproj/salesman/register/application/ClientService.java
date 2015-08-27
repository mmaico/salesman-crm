package br.com.kproj.salesman.register.application;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface ClientService extends ModelService<Person> {

    Client register(Client client);
}
