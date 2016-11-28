package br.com.kproj.salesman.accounts.contacts.domain.model.contact;


import br.com.kproj.salesman.accounts.contacts.domain.model.customer.Customer;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Pageable;

public interface ContactRepository extends BaseRepository<Contact, Long> {


    Iterable<Contact> findAll(Customer customer, Pageable pager);

    Contact update(Contact contact);

}

