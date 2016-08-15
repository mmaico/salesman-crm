package br.com.kproj.salesman.register.application.contract;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;


public interface ContactApplication extends ModelLegacyService<Contact> {

    Contact register(Contact contact);

    Contact register(Contact contact, Client client);

    Contact register(Contact contact, Provider provider);

    List<Contact> getContactsByClient(Client client);

    List<Contact> getContactsByProvider(Provider provider);
}
