package br.com.kproj.salesman.accounts.contacts.infrastructure.persistence.translate;


import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.ContactBuilder;
import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;


@Component
public class ContactEntityToContactConverter implements Converter<ContactEntity, Contact> {

    @Override
    public Contact convert(ContactEntity contactEntity, Object... args) {
        if (contactEntity == null) return null;

        Contact contact = ContactBuilder.createContact(contactEntity.getId())
                .withName(contactEntity.getName())
                .withPhone(contactEntity.getPhone())
                .withEmail(contactEntity.getEmail())
                .withCustomer(contactEntity.getCustomer().getId())
                .withPosition(contactEntity.getPosition()).build();

        return contact;
    }
}
