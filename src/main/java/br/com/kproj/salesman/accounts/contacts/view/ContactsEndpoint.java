package br.com.kproj.salesman.accounts.contacts.view;


import br.com.kproj.salesman.accounts.contacts.application.ContactFacade;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.ContactBuilder;
import br.com.kproj.salesman.accounts.contacts.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.contacts.view.support.builders.ContactResourceBuilder;
import br.com.kproj.salesman.accounts.contacts.view.support.resources.ContactResource;
import br.com.kproj.salesman.accounts.contacts.view.support.update.ContactUpdateFields;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.com.kproj.salesman.accounts.contacts.domain.model.contact.ContactForCustomer.createContactToCustomer;


@RestController("contactEndpointAccountsModule")
public class ContactsEndpoint {

    private ContactFacade service;

    private ContactResourceBuilder builder;

    private ContactUpdateFields updateFields;


    @Autowired
    public ContactsEndpoint(ContactFacade service, ContactResourceBuilder builder, ContactUpdateFields updateFields) {
        this.service = service;
        this.builder = builder;
        this.updateFields = updateFields;
    }

    @RequestMapping(value = "/rs/customers/{customerId}/contacts", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PathVariable Long customerId, @PageableDefault(size = 100) Pageable pageable) {
        Customer customer = new Customer(customerId);

        Iterable<Contact> contacts = service.findAll(customer, pageable);

        return builder.build(contacts);
    }

    @RequestMapping(value = "/rs/customers/contacts/{contactId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long contactId) {

        Optional<Contact> resultContact = service.getOne(contactId);
        Contact contact = resultContact.orElseThrow(NotFoundException::new);

        return builder.build(contact);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/customers/{customerId}/contacts", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long customerId, @RequestBody ContactResource resource) {

        Contact contact = ContactBuilder.createContact()
                .withPosition(resource.getPosition())
                .withName(resource.getName())
                .withPhone(resource.getPhone())
                .withEmail(resource.getEmail())
                .withCustomer(customerId)
                .withPosition(resource.getPosition()).build();

        Optional<Contact> contactCreated = service.register(createContactToCustomer(customerId, contact));

        return builder.build(contactCreated.get());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/customers/contacts/{contactId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResourceItem update(@PathVariable Long contactId, @RequestBody ContactResource resource) {

        Contact contact = ContactBuilder.createContact(contactId)
                .withPosition(resource.getPosition())
                .withName(resource.getName())
                .withPhone(resource.getPhone())
                .withEmail(resource.getEmail())
                .withPosition(resource.getPosition()).build();

        updateFields.addFieldsToUpdate(contact);
        Contact contactSaved = service.update(contact);

        return builder.build(contactSaved);
    }


}
