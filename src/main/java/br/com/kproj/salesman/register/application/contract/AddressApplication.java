package br.com.kproj.salesman.register.application.contract;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.service.ModelService;


public interface AddressApplication extends ModelService<Address> {

    Address register(Client person, Address address);
}
