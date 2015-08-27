package br.com.kproj.salesman.register.application.impl;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.AddressRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.impl.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.AddressService;
import br.com.kproj.salesman.register.application.ClientService;

@Service
public class AddressServiceImpl extends BaseModelServiceImpl<Address> implements AddressService {

    private AddressRepository addressRepository;

    private ClientService clientService;

    @Autowired
    public AddressServiceImpl(ClientService clientService, AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
        this.clientService = clientService;
    }

    @Override
    public Address register(Person person, Address address) {

        if (address.isNew()) {
            Optional<Person> clientLoaded = clientService.getOne(person.getId());
            if (clientLoaded.isPresent()) {
                clientLoaded.get().addAddress(address);
            } else {
                throw new ValidationException(newHashSet("client.not.exist.on.save.address"));
            }
            clientService.save(clientLoaded.get());
            return address;
        } else {
            return super.save(address);
        }
    }

    @Override
    public BaseRepository<Address, Long> getRepository() {
        return this.addressRepository;
    }
}
