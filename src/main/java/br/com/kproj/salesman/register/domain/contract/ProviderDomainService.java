package br.com.kproj.salesman.register.domain.contract;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.service.DomainBusinessRulesLegacy;

public interface ProviderDomainService extends DomainBusinessRulesLegacy<Person> {



    void checkBusinessRulesFor(Person provider);
}
