package br.com.kproj.salesman.register.application.impl;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.AddressRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.impl.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.AddressService;
import br.com.kproj.salesman.register.application.ClientService;
import br.com.kproj.salesman.register.domain.AddressDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.collect.Sets.newHashSet;

@Service
public class AddressServiceImpl extends BaseModelServiceImpl<Address> implements AddressService {

    private AddressRepository addressRepository;

    private ClientService clientService;
    
    private AddressDomainService service;

    @Autowired
    public AddressServiceImpl(ClientService clientService, AddressRepository addressRepository,
    		AddressDomainService service) {
        this.addressRepository = addressRepository;
        this.clientService = clientService;
        this.service = service;
    }

    @Override
    public Address register(Client client, Address address) {

        if (address.isNew()) {
            Optional<Person> personLoaded = clientService.getOne(client.getId());
            if (personLoaded.isPresent()) {
                address.setPerson(personLoaded.get());
            } else {
                throw new ValidationException(newHashSet("client.not.exist.on.save.address"));
            }
            service.prepareToSave(address);
            return super.save(address);
        } else {
            service.prepareToSave(address);
            return super.save(address);
        }
    }

    @Override
    public BaseRepository<Address, Long> getRepository() {
        return this.addressRepository;
    }
}
