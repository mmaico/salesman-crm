package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.AddressRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.AddressApplication;
import br.com.kproj.salesman.register.application.contract.ClientApplication;
import br.com.kproj.salesman.register.domain.contract.AddressDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.collect.Sets.newHashSet;

@Service
public class AddressApplicationImpl extends BaseModelServiceImpl<Address> implements AddressApplication {

    private final AddressRepository addressRepository;

    private final ClientApplication clientApplication;

    private final AddressDomainService service;

    @Autowired
    public AddressApplicationImpl(ClientApplication clientApplication, AddressRepository addressRepository, AddressDomainService service) {
        this.addressRepository = addressRepository;
        this.clientApplication = clientApplication;
        this.service = service;
    }

    @Override
    public Address register(Client client, Address address) {

        if (address.isNew()) {
            Optional<Person> personLoaded = clientApplication.getOne(client.getId());
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
