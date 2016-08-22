package br.com.kproj.salesman.register.application.contract;

import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;


public interface ContactApplication extends ModelLegacyService<ContactEntity> {

    ContactEntity register(ContactEntity contact);

    ContactEntity register(ContactEntity contact, Client client);

    ContactEntity register(ContactEntity contact, Provider provider);

    List<ContactEntity> getContactsByClient(Client client);

    List<ContactEntity> getContactsByProvider(Provider provider);
}
