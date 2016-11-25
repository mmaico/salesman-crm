package br.com.kproj.salesman.accounts.customers.view;


import br.com.kproj.salesman.accounts.customers.application.CustomerFacade;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.CustomerBuilder;
import br.com.kproj.salesman.accounts.customers.view.support.builders.CustomerResourceBuilder;
import br.com.kproj.salesman.accounts.customers.view.support.resources.CustomerResource;
import br.com.kproj.salesman.accounts.customers.view.support.update.CustomerUpdateFields;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("customerEndpointDeliveryModule")
public class CustomerEndpoint {

    private CustomerFacade service;

    private CustomerResourceBuilder builder;

    private CustomerUpdateFields customerFields;


    @Autowired
    public CustomerEndpoint(CustomerFacade service, CustomerResourceBuilder builder, CustomerUpdateFields customerFields) {
        this.service = service;
        this.builder = builder;
        this.customerFields = customerFields;
    }

    @RequestMapping(value = "/rs/customers", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getCustomers(@PageableDefault(size = 100) Pageable pageable) {

        Iterable<Customer> customers = service.findAll(pageable);

        return builder.build(customers);
    }

    @RequestMapping(value = "/rs/customers/{customerId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getTask(@PathVariable Long customerId) {

        Optional<Customer> customerOptional = service.getOne(customerId);
        Customer customer = customerOptional.orElseThrow(NotFoundException::new);

        return builder.build(customer);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/customers", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@RequestBody CustomerResource resource) {

        Customer customer = CustomerBuilder.createCustomer()
                .withName(resource.getName())
                .withDescription(resource.getDescription())
                .withSite(resource.getSite())
                .build();

        Optional<Customer> customerSaved = service.register(customer);

        return builder.build(customerSaved.get());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/customers/{customerId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResourceItem create(@PathVariable Long customerId, @RequestBody CustomerResource resource) {

        Customer customer = CustomerBuilder.createCustomer(customerId)
                .withName(resource.getName())
                .withDescription(resource.getDescription())
                .withSite(resource.getSite())
                .build();

        customerFields.addFieldsToUpdate(customer);

        Optional<Customer> customerSaved = service.update(customer);

        return builder.build(customerSaved.get());
    }

}
