package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.ContactRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import br.com.kproj.salesman.register.application.contract.ClientApplication;
import br.com.kproj.salesman.register.application.contract.ContactApplication;
import br.com.kproj.salesman.register.application.contract.ProviderApplication;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactApplicationImpl extends BaseModelServiceLegacyImpl<ContactEntity> implements ContactApplication {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ClientApplication clientapplication;
    @Autowired
    private ProviderApplication providerApplication;


    @Override
    public ContactEntity register(ContactEntity contact) {
        return super.save(contact);
    }

    @Override
    public ContactEntity register(ContactEntity contact, Client client) {

        if (client.getId() == null || !clientapplication.getOne(client.getId()).isPresent()) {
            throw new ValidationException(Sets.newHashSet("contact.invalid.client"));
        }

        contact.setPerson(client.to());

        return super.save(contact);
    }

    @Override
    public ContactEntity register(ContactEntity contact, Provider provider) {
        if (provider.getId() == null || !providerApplication.getOne(provider.getId()).isPresent()) {
            throw new ValidationException(Sets.newHashSet("contact.invalid.provider"));
        }

        contact.setPerson(provider.to());

        return super.save(contact);
    }

    public List<ContactEntity> getContactsByClient(Client client) {
        return contactRepository.findByPerson(client.to());
    }

    public List<ContactEntity> getContactsByProvider(Provider provider) {
        return contactRepository.findByPerson(provider.to());
    }

    public BaseRepositoryLegacy<ContactEntity, Long> getRepository() {
        return this.contactRepository;
    }
}
