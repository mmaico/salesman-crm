package br.com.kproj.salesman.accounts.application.impl;

import br.com.kproj.salesman.accounts.application.ContactFacade;
import br.com.kproj.salesman.accounts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.domain.model.contact.ContactRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends BaseModelServiceImpl<Contact> implements ContactFacade {

    private ContactRepository repository;

    @Autowired
    public ContactServiceImpl(ContactRepository repository) {
        this.repository = repository;
    }


    @Override
    public BaseRepository<Contact, Long> getRepository() {
        return repository;
    }
}
