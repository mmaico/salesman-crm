package br.com.kproj.salesman.accounts.contacts.domain.model.user;


import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.ContactRepository;
import br.com.kproj.salesman.accounts.contacts.domain.services.ContactToCustomerService;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

import com.github.mmaico.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    @Autowired
    private ContactRepository repository;

    public User() {
        AutowireHelper.autowire(this);
    }

    public ContactToCustomerService save(Contact contact) {
        return (customer -> {
            contact.setCustomer(customer);
            return repository.save(contact);
        });
    }

    public Contact update(Contact contact) {
        return repository.update(contact);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static User user() {
        return new User();
    }
}
