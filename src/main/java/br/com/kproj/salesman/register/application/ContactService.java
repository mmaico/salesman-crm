package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.service.ModelService;


public interface ContactService extends ModelService<Contact> {

    Contact register(Person person, Contact contact);
}
