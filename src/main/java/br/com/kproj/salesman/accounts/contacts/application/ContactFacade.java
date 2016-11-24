package br.com.kproj.salesman.accounts.contacts.application;



import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.domain.model.customer.Customer;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import org.springframework.data.domain.Pageable;

public interface ContactFacade extends ModelFacade<Contact> {

    Iterable<Contact> findAll(Customer customer, Pageable pager);
}
