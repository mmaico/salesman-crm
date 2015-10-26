package br.com.kproj.salesman.register.application.contract;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.service.ModelService;


public interface ContactService extends ModelService<Contact> {

    Contact register(Contact contact);
}
