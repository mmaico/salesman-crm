package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.service.ModelService;


public interface AddressService extends ModelService<Address> {

    Address register(Client person, Address address);
}
