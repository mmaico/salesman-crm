package br.com.kproj.salesman.accounts.contacts.application.validators;


import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.ContactValidator;
import br.com.kproj.salesman.accounts.contacts.domain.model.customer.CustomerRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.accounts.contacts.application.validators.ContactIgnoreRules.*;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class ContactBusinessRules implements ContactValidator {

    private CustomerRepository customerRepository;

    @Autowired
    public ContactBusinessRules(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    Map<String, CheckRule<Contact>> persistRules = new HashMap<>();
    {
        persistRules.put(ruleInvalidCustomer(), (contact) -> contact.getCustomer().isNew()
                || !customerRepository.findOne(contact.getCustomer().getId()).isPresent());

        persistRules.put(ruleInvalidName(), (contact) ->
                contact.isNew()
                        ? isBlank(contact.getName())
                        : contact.hasField("name") && isBlank(contact.getName())
        );

        persistRules.put(ruleInvalidEmail(), (contact) ->
                contact.isNew()
                        ? !isBlank(contact.getEmail()) && !EmailValidator.getInstance().isValid(contact.getEmail())
                        : contact.hasField("email") && !isBlank(contact.getEmail()) && !EmailValidator.getInstance().isValid(contact.getEmail())
        );
    }

    @Override
    public void checkRules(Contact contact) {
        checkRules(contact, ContactIgnoreRules.add(EMPTY));
    }

    @Override
    public void checkRules(Contact contact, ContactIgnoreRules ignoreRules) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(rule -> {
                    try {
                        if (ignoreRules.contains(rule.getKey())) return Boolean.FALSE;

                        return rule.getValue().check(contact);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

    }
}
