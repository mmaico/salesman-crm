package br.com.kproj.salesman.accounts.contacts.application.impl;


import br.com.kproj.salesman.accounts.contacts.application.ContactFacade;
import br.com.kproj.salesman.accounts.contacts.application.validators.ContactIgnoreRules;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.ContactForCustomer;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.ContactRepository;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.ContactValidator;
import br.com.kproj.salesman.accounts.contacts.domain.model.customer.Customer;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.accounts.contacts.application.validators.ContactIgnoreRules.ruleInvalidCustomer;
import static br.com.kproj.salesman.accounts.contacts.domain.model.user.User.user;

@Service
public class ContactServiceImpl extends BaseModelServiceImpl<Contact> implements ContactFacade {

    private ContactRepository repository;

    private ContactValidator validator;

    @Autowired
    public ContactServiceImpl(ContactRepository repository, ContactValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }


    @Override
    public Optional<Contact> register(ContactForCustomer contactForCustomer) {
        Contact contact = contactForCustomer.getContact();
        Customer customer = contactForCustomer.getCustomer();
        contact.setCustomer(customer);

        validator.checkRules(contact);

        return user().save(contact).to(customer);
    }

    @Override
    public Contact update(Contact contact) {
        validator.checkRules(contact, ContactIgnoreRules.add(ruleInvalidCustomer()));

        return user().update(contact);
    }

    @Override
    public Iterable<Contact> findAll(Customer customer, Pageable pager) {
        return repository.findAll(customer, pager);
    }

    @Override
    public BaseRepository<Contact, Long> getRepository() {
        return repository;
    }
}
