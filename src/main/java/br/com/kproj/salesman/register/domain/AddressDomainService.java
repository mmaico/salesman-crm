package br.com.kproj.salesman.register.domain;


import br.com.kproj.salesman.infrastructure.entity.Address;

import java.util.List;

public interface AddressDomainService {

    void prepareToSave(List<Address> addresses);

    void prepareToSave(Address address);
}
