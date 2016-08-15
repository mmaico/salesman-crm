package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.AddressRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import br.com.kproj.salesman.register.application.contract.AddressApplication;
import br.com.kproj.salesman.register.application.contract.ClientApplication;
import br.com.kproj.salesman.register.application.contract.ProviderApplication;
import br.com.kproj.salesman.register.domain.contract.AddressDomainService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressApplicationImpl extends BaseModelServiceLegacyImpl<Address> implements AddressApplication {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientApplication clientApplication;

    @Autowired
    private ProviderApplication providerApplication;

    @Autowired
    private AddressDomainService service;


    @Override
    public Address register(Address address, Client client) {

        if (client.getId() == null || !clientApplication.getOne(client.getId()).isPresent()) {
            throw new ValidationException(Sets.newHashSet("address.invalid.client"));
        }

        address.setPerson(client.to());
        service.prepareToSave(address);

        return super.save(address);
    }

    @Override
    public Address register(Address address, Provider provider) {
        if (provider.getId() == null || !providerApplication.getOne(provider.getId()).isPresent()) {
            throw new ValidationException(Sets.newHashSet("address.invalid.provider"));
        }

        address.setPerson(provider.to());
        service.prepareToSave(address);

        return super.save(address);
    }

    @Override
    public List<Address> getAddressesByClient(Client client) {
        return addressRepository.findByPerson(client.to());
    }

    @Override
    public List<Address> getAddressesByProvider(Provider provider) {
        return addressRepository.findByPerson(provider.to());
    }

    public BaseRepositoryLegacy<Address, Long> getRepository() {
        return this.addressRepository;
    }
}
