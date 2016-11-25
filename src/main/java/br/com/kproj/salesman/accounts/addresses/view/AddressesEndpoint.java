package br.com.kproj.salesman.accounts.addresses.view;


import br.com.kproj.salesman.accounts.addresses.application.AddressFacade;
import br.com.kproj.salesman.accounts.addresses.domain.model.address.Address;
import br.com.kproj.salesman.accounts.addresses.domain.model.address.AddressBuilder;
import br.com.kproj.salesman.accounts.addresses.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.addresses.view.support.builders.AddressResourceBuilder;
import br.com.kproj.salesman.accounts.addresses.view.support.resources.AddressResource;
import br.com.kproj.salesman.accounts.addresses.view.support.update.AddressUpdateFields;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("addressesEndpointAccountsModule")
public class AddressesEndpoint {

    private AddressFacade service;

    private AddressResourceBuilder builder;

    private AddressUpdateFields updateFields;


    @Autowired
    public AddressesEndpoint(AddressFacade service, AddressResourceBuilder builder, AddressUpdateFields updateFields) {
        this.service = service;
        this.builder = builder;
        this.updateFields = updateFields;
    }

    @RequestMapping(value = "/rs/customers/{customerId}/addresses", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PathVariable Long customerId, @PageableDefault(size = 100) Pageable pageable) {
        Customer customer = new Customer(customerId);

        Iterable<Address> addresses = service.findAll(customer, pageable);

        return builder.build(addresses);
    }

    @RequestMapping(value = "/rs/customers/addresses/{addressId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getOne(@PathVariable Long addressId) {

        Optional<Address> addressOptional = service.getOne(addressId);
        Address address = addressOptional.orElseThrow(NotFoundException::new);

        return builder.build(address);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/customers/{customerId}/addresses", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long customerId, @RequestBody AddressResource resource) {

        Address address = AddressBuilder.createAddress()
                .withCity(resource.getCity())
                .withCountry(resource.getCountry())
                .withState(resource.getState())
                .withStreet(resource.getStreet())
                .withZipCode(resource.getZipCode())
                .withCustomer(new Customer(customerId))
                .withType(resource.getType())
                .build();

        Optional<Address> addressOptional = service.register(address);

        return builder.build(addressOptional.get());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/customers/addresses/{addressId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResourceItem update(@PathVariable Long addressId, @RequestBody AddressResource resource) {

        Address address = AddressBuilder.createAddress(addressId)
                .withCity(resource.getCity())
                .withCountry(resource.getCountry())
                .withState(resource.getState())
                .withStreet(resource.getStreet())
                .withZipCode(resource.getZipCode())
                .withType(resource.getType())
                .build();

        updateFields.addFieldsToUpdate(address);
        Optional<Address> addressOptional = service.update(address);

        return builder.build(addressOptional.get());
    }


}
