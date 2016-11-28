package br.com.kproj.salesman.accounts.contacts.domain.services;

import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.domain.model.customer.Customer;

import java.util.Optional;

@FunctionalInterface
public interface ContactToCustomerService {


    Optional<Contact> to(Customer customer);
}
