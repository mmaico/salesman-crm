package br.com.kproj.salesman.accounts.contacts.infrastructure.persistence;

import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.ContactRepository;
import br.com.kproj.salesman.accounts.contacts.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.contacts.infrastructure.persistence.springdata.ContactEntityRepositorySpringData;
import br.com.kproj.salesman.accounts.contacts.infrastructure.persistence.translate.ContactEntityToContactConverter;
import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;

@Repository
public class ContactRepositoryHibernate extends BaseRespositoryImpl<Contact, ContactEntity> implements ContactRepository {

    private ContactEntityRepositorySpringData repository;

    private ContactEntityToContactConverter converter;

    @Autowired
    public ContactRepositoryHibernate(ContactEntityRepositorySpringData repository, ContactEntityToContactConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    @Override
    public BaseRepositoryLegacy<ContactEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ContactEntity, Contact> getConverter() {
        return converter;
    }

    @Override
    public Iterable<Contact> findAll(Customer customer, Pageable pageable) {

        Page<ContactEntity> result = repository.findAll(customer.getId(), pageable);

        List<Contact> contacts = result.getContent().stream().map(entity -> getConverter()
                .convert(entity)).collect(Collectors.toList());

        return new PageImpl<>(contacts, pageable, result.getTotalElements());
    }

    @Override
    public Contact update(Contact contact) {
        ContactEntity contactEntity = repository.findOne(contact.getId());
        from(contact).merge(contactEntity);
        repository.save(contactEntity);

        return getConverter().convert(contactEntity);
    }

    @Override
    public Optional<Contact> save(Contact contact) {
        ContactEntity contactEntity = from(contact).convertTo(ContactEntity.class);
        from(contact).merge(contactEntity);

        Contact contactSaved = getConverter().convert(repository.save(contactEntity));

        return Optional.of(contactSaved);
    }
}
