package br.com.kproj.salesman.register.application.impl;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.ContactRepository;
import br.com.kproj.salesman.infrastructure.service.impl.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends BaseModelServiceImpl<Contact> implements ContactService {

    private ContactRepository contactRepository;


    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;

    }

    @Override
    public Contact register(Contact contact) {
        return super.save(contact);
    }

    @Override
    public BaseRepository<Contact, Long> getRepository() {
        return this.contactRepository;
    }
}
