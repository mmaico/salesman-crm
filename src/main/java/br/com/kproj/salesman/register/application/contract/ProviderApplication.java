package br.com.kproj.salesman.register.application.contract;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface ProviderApplication extends ModelService<Person> {

    Provider register(Provider client);
}
