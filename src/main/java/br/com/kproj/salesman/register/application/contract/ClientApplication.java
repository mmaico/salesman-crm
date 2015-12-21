package br.com.kproj.salesman.register.application.contract;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface ClientApplication extends ModelService<Person> {

    Client register(Client client);
}
