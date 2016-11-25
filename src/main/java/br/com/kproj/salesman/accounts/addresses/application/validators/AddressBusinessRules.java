package br.com.kproj.salesman.accounts.addresses.application.validators;

import br.com.kproj.salesman.accounts.addresses.domain.model.address.Address;
import br.com.kproj.salesman.accounts.addresses.domain.model.address.AddressValidator;
import br.com.kproj.salesman.accounts.addresses.domain.model.customer.CustomerRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.accounts.addresses.application.validators.AddressIgnoreRules.*;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class AddressBusinessRules implements AddressValidator {

    private CustomerRepository customerRepository;

    @Autowired
    public AddressBusinessRules(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    Map<String, CheckRule<Address>> persistRules = new HashMap<>();
    {
        persistRules.put(ruleInvalidCustomer(), (address) -> address.getCustomer().isNew()
                || !customerRepository.findOne(address.getCustomer().getId()).isPresent());

        persistRules.put(ruleInvalidStreet(), (address) ->
            address.isNew()
                        ? isBlank(address.getStreet())
                        : address.hasField("street") && isBlank(address.getStreet())
        );

        persistRules.put(ruleInvalidType(), (address) ->
                address.isNew()
                        ? address.getType() == null || Address.Type.UNINFORMED.equals(address.getType())
                        : address.hasField("type") && (address.getType() == null || Address.Type.UNINFORMED.equals(address.getType()))
        );
    }

    @Override
    public void checkRules(Address address) {
        checkRules(address, AddressIgnoreRules.add(EMPTY));
    }

    @Override
    public void checkRules(Address address, AddressIgnoreRules ignoreRules) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(rule -> {
                    try {
                        if (ignoreRules.contains(rule.getKey())) return Boolean.FALSE;

                        return rule.getValue().check(address);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

    }
}
