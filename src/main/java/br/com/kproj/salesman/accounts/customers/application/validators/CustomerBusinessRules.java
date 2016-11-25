package br.com.kproj.salesman.accounts.customers.application.validators;

import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.CustomerRepository;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.CustomerValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class CustomerBusinessRules implements CustomerValidator {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerBusinessRules(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    Map<String, CheckRule<Customer>> persistRules = new HashMap<>();
    {
        persistRules.put("customer.invalid.id", (customer) -> customer.isNew() || !customerRepository.findOne(customer.getId()).isPresent());
        persistRules.put("customer.invalid.name", (customer) -> isBlank(customer.getName()));
    }

    @Override
    public void checkRules(Customer customer) {
        checkRules(customer, CustomerIgnoreRules.add(EMPTY));
    }

    @Override
    public void checkRules(Customer customer, CustomerIgnoreRules ignoreRules) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(rule -> {
                    try {
                        if (ignoreRules.contains(rule.getKey())) return Boolean.FALSE;

                        return rule.getValue().check(customer);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

    }
}
