package br.com.kproj.salesman.accounts.addresses.domain.model.address;


import br.com.kproj.salesman.accounts.addresses.application.validators.AddressIgnoreRules;

public interface AddressValidator {

    void checkRules(Address address);
    void checkRules(Address address, AddressIgnoreRules ignoreRules);
}
