package br.com.kproj.salesman.medias.storage.application.validators;


import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinition;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinitionValidator;
import org.apache.commons.validator.EmailValidator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.accounts.contacts.application.validators.ContactIgnoreRules.ruleInvalidEmail;
import static br.com.kproj.salesman.accounts.contacts.application.validators.ContactIgnoreRules.ruleInvalidName;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class StorageDefinitionBusinessRules implements StorageDefinitionValidator {



    Map<String, CheckRule<StorageDefinition>> persistRules = new HashMap<>();
    {

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
    public void checkRules(StorageDefinition storageDefinition) {
        checkRules(storageDefinition, StorageDefinitionIgnoreRules.add(EMPTY));
    }

    @Override
    public void checkRules(StorageDefinition storageDefinition, StorageDefinitionIgnoreRules ignoreRules) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(rule -> {
                    try {
                        if (ignoreRules.contains(rule.getKey())) return Boolean.FALSE;

                        return rule.getValue().check(storageDefinition);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

    }
}
