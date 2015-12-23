package br.com.kproj.salesman.register.application.contract;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.List;


public interface AddressApplication extends ModelService<Address> {


    Address register(Address address, Client client);

    Address register(Address address, Provider provider);

    List<Address> getAddressesByClient(Client client);

    List<Address> getAddressesByProvider(Provider provider);
}
