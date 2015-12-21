package br.com.kproj.salesman.register.domain.contract;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.service.DomainBusinessRules;

public interface ProviderDomainService extends DomainBusinessRules<Person> {



    void checkBusinessRulesFor(Person provider);
}
