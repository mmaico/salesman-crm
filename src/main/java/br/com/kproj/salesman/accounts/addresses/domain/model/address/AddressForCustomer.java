package br.com.kproj.salesman.accounts.addresses.domain.model.address;


import br.com.kproj.salesman.accounts.addresses.domain.model.customer.Customer;

public class AddressForCustomer {

    private final Address address;
    private final Long customerId;

    public AddressForCustomer(Address address, Long customerId) {
        this.address = address;
        this.customerId = customerId;
    }

    public Address getAddress() {
        return address;
    }

    public Customer getCustomer() {
        return new Customer(customerId);
    }

    public static AddressForCustomer createAddressToCustomer(Long customerId, Address address) {
        return new AddressForCustomer(address, customerId);
    }
}
