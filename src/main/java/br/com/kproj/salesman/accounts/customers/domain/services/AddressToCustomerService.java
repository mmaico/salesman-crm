package br.com.kproj.salesman.accounts.customers.domain.services;

import br.com.kproj.salesman.accounts.addresses.domain.model.address.Address;
import br.com.kproj.salesman.accounts.addresses.domain.model.customer.Customer;

import java.util.Optional;

@FunctionalInterface
public interface AddressToCustomerService {


    Optional<Address> to(Customer customer);
}
