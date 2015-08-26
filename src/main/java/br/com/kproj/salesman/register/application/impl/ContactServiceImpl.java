package br.com.kproj.salesman.register.application.impl;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.ContactRepository;
import br.com.kproj.salesman.infrastructure.service.impl.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.ClientService;
import br.com.kproj.salesman.register.application.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.collect.Sets.newHashSet;

@Service
public class ContactServiceImpl extends BaseModelServiceImpl<Contact> implements ContactService {

    private ContactRepository contactRepository;

    private ClientService clientService;

    @Autowired
    public ContactServiceImpl(ClientService clientService, ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
        this.clientService = clientService;
    }

    @Override
    public Contact register(Person person, Contact contact) {

        if (contact.isNew()) {
            Optional<Person> clientLoaded = clientService.getOne(person.getId());
            if (clientLoaded.isPresent()) {
                clientLoaded.get().addContact(contact);
            } else {
                throw new ValidationException(newHashSet("client.not.exist.on.save.contact"));
            }
            clientService.save(clientLoaded.get());
            return contact;
        } else {
            return super.save(contact);
        }
    }

    @Override
    public BaseRepository<Contact, Long> getRepository() {
        return this.contactRepository;
    }
}
