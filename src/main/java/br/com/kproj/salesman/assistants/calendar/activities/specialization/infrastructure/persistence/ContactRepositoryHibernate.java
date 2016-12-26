package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence;


import br.com.kproj.salesman.accounts.contacts.infrastructure.persistence.springdata.ContactEntityRepositorySpringData;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.contact.Contact;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.contact.ContactRepository;
import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("contactRepositoryHibernateCalendarModule")
public class ContactRepositoryHibernate extends BaseRespositoryImpl<Contact, ContactEntity> implements ContactRepository {


    private ContactEntityRepositorySpringData repository;

    @Autowired
    public ContactRepositoryHibernate(ContactEntityRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<ContactEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ContactEntity, Contact> getConverter() {
        return ((contactEntity, args) -> new Contact(contactEntity.getId()));
    }
}
