package br.com.kproj.salesman.accounts.addresses.application.impl;


import br.com.kproj.salesman.accounts.addresses.application.AddressFacade;
import br.com.kproj.salesman.accounts.addresses.application.validators.AddressIgnoreRules;
import br.com.kproj.salesman.accounts.addresses.domain.model.address.Address;
import br.com.kproj.salesman.accounts.addresses.domain.model.address.AddressForCustomer;
import br.com.kproj.salesman.accounts.addresses.domain.model.address.AddressRepository;
import br.com.kproj.salesman.accounts.addresses.domain.model.address.AddressValidator;
import br.com.kproj.salesman.accounts.addresses.domain.model.customer.Customer;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.accounts.addresses.application.validators.AddressIgnoreRules.ruleInvalidCustomer;
import static br.com.kproj.salesman.accounts.addresses.domain.model.user.User.user;

@Service
public class AddressServiceImpl extends BaseModelServiceImpl<Address> implements AddressFacade {

    private AddressRepository repository;

    private AddressValidator validator;

    @Autowired
    public AddressServiceImpl(AddressRepository repository, AddressValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Page<Address> findAll(Customer customer, Pageable pageable) {
        return repository.findAll(customer, pageable);
    }

    public Optional<Address> register(AddressForCustomer addressForCustomer) {
        Address address = addressForCustomer.getAddress();
        Customer customer = addressForCustomer.getCustomer();
        address.setCustomer(customer);

        validator.checkRules(address);

        return user().save(address).to(customer);
    }

    @Override
    public Optional<Address> update(Address address) {
        validator.checkRules(address, AddressIgnoreRules.add(ruleInvalidCustomer()));

        return user().update(address);
    }

    @Override
    public BaseRepository<Address, Long> getRepository() {
        return repository;
    }


}
