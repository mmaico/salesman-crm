package br.com.kproj.salesman.accounts.customers.application;


import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface CustomerFacade extends ModelFacade<Customer> {

    Optional<Customer> update(Customer customer);

}
