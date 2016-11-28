package br.com.kproj.salesman.accounts.contacts.domain.model.contact;


import br.com.kproj.salesman.accounts.contacts.application.validators.ContactIgnoreRules;

public interface ContactValidator {

    void checkRules(Contact contact);

    void checkRules(Contact contact, ContactIgnoreRules ignoreRules);
}
