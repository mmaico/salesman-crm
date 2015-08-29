package br.com.kproj.salesman.register.domain.impl;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.location.Country;
import br.com.kproj.salesman.register.domain.AddressDomainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressDomainServiceImpl implements AddressDomainService {


    @Override
    public void prepareToSave(List<Address> addresses) {

        addresses.forEach(e -> prepareToSave(e));
    }

    @Override
    public void prepareToSave(Address address) {

        if (!Country.BRASIL.equals(address.getCountry().getId())) {
            address.setCity(null);
        }
    }
}
