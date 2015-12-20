package br.com.kproj.salesman.register.application.contract;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.service.ModelService;


public interface ContactService extends ModelService<Contact> {

    Contact register(Contact contact);

    Contact register(Contact contact, Client client);

    Contact register(Contact contact, Provider provider);
}
