package br.com.kproj.salesman.accounts.application.impl;

import br.com.kproj.salesman.accounts.application.AddressFacade;
import br.com.kproj.salesman.accounts.domain.model.address.Address;
import br.com.kproj.salesman.accounts.domain.model.address.AddressRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends BaseModelServiceImpl<Address> implements AddressFacade {

    private AddressRepository repository;

    @Autowired
    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepository<Address, Long> getRepository() {
        return repository;
    }
}
