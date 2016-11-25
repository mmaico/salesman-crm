package br.com.kproj.salesman.accounts.addresses.application;



import br.com.kproj.salesman.accounts.addresses.domain.model.address.Address;
import br.com.kproj.salesman.accounts.addresses.domain.model.address.AddressForCustomer;
import br.com.kproj.salesman.accounts.addresses.domain.model.customer.Customer;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AddressFacade extends ModelFacade<Address> {

    Page<Address> findAll(Customer customer, Pageable pageable);

    Optional<Address> register(AddressForCustomer addressForCustomer);

    Optional<Address> update(Address address);
}
