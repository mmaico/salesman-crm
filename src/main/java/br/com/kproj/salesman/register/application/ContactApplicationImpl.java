package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.ContactRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.ContactApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactApplicationImpl extends BaseModelServiceImpl<Contact> implements ContactApplication {

    private ContactRepository contactRepository;




    @Autowired
    public ContactApplicationImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;

    }

    @Override
    public Contact register(Contact contact) {
        return super.save(contact);
    }

    @Override
    public Contact register(Contact contact, Client client) {
       // if (client.getId() == null || )
        return null;
    }

    @Override
    public Contact register(Contact contact, Provider provider) {
        return null;
    }



    @Override
    public BaseRepository<Contact, Long> getRepository() {
        return this.contactRepository;
    }
}
