package br.com.kproj.salesman.accounts.customers.application.impl;

import br.com.kproj.salesman.accounts.customers.application.CustomerFacade;
import br.com.kproj.salesman.accounts.customers.application.validators.CustomerIgnoreRules;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.CustomerRepository;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.CustomerValidator;
import br.com.kproj.salesman.accounts.customers.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.accounts.customers.application.validators.CustomerIgnoreRules.ruleInvalidId;
import static br.com.kproj.salesman.accounts.customers.domain.model.user.User.user;

@Service
public class CustomerServiceImpl extends BaseModelServiceImpl<Customer> implements CustomerFacade {

    private CustomerRepository repository;

    private CustomerValidator validator;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository, CustomerValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Optional<Customer> register(Customer customer) {
        validator.checkRules(customer, CustomerIgnoreRules.add(ruleInvalidId()));

        return user().save(customer);
    }

    @Override
    public Optional<Customer> update(Customer customer) {
        validator.checkRules(customer);

        return user().update(customer);
    }


    @Override
    public BaseRepository<Customer, Long> getRepository() {
        return repository;
    }
}
