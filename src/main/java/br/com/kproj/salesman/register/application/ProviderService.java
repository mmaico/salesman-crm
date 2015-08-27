package br.com.kproj.salesman.register.application;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface ProviderService extends ModelService<Person> {

    Provider register(Provider client);
}
