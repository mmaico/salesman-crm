package br.com.kproj.salesman.accounts.customers.infrastructure.persistence.translate;

import br.com.kproj.salesman.accounts.customers.domain.model.address.Address;
import br.com.kproj.salesman.accounts.customers.domain.model.address.AddressBuilder;
import br.com.kproj.salesman.infrastructure.entity.accounts.AddressEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;


@Component
public class AddressEntityToAddressConverter implements Converter<AddressEntity, Address> {

    @Override
    public Address convert(AddressEntity addressEntity, Object... args) {
        if (addressEntity == null) return null;

        Address address = AddressBuilder.createAddress(addressEntity.getId())
                    .withCity(addressEntity.getCity())
                    .withCountry(addressEntity.getCountry())
                    .withState(addressEntity.getState())
                    .withStreet(addressEntity.getStreet())
                    .withZipCode(addressEntity.getZipCode())
                    .withType(Address.Type.getByName(addressEntity.getType().getName()))
                .build();

        return address;
    }
}
