package br.com.kproj.salesman.accounts.customers.domain.model.customer;


import br.com.kproj.salesman.accounts.customers.application.validators.CustomerIgnoreRules;

public interface CustomerValidator {

    void checkRules(Customer customer);

    void checkRules(Customer customer, CustomerIgnoreRules ignoreRules);
}
