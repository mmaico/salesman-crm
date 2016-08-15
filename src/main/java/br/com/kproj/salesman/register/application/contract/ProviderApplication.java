package br.com.kproj.salesman.register.application.contract;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface ProviderApplication extends ModelLegacyService<Person> {

    Provider register(Provider client);
}
