package br.com.kproj.salesman.medias.storage.view;


import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.medias.storage.application.StorageDefinitionFacade;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinition;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinitionBuilder;
import br.com.kproj.salesman.medias.storage.view.support.builders.ContactResourceBuilder;
import br.com.kproj.salesman.medias.storage.view.support.resources.StorageDefinitionResource;
import br.com.kproj.salesman.medias.storage.view.support.update.ContactUpdateFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("contactEndpointAccountsModule")
public class StorageDefinitionEndpoint {

    private StorageDefinitionFacade service;

    private ContactResourceBuilder builder;

    private ContactUpdateFields updateFields;


    @Autowired
    public StorageDefinitionEndpoint(StorageDefinitionFacade service, ContactResourceBuilder builder, ContactUpdateFields updateFields) {
        this.service = service;
        this.builder = builder;
        this.updateFields = updateFields;
    }

    //@RequestMapping(value = "/rs/customders/{customerId}/contacts", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PathVariable Long customerId, @PageableDefault(size = 100) Pageable pageable) {
    //    Customer customer = new Customer(customerId);

      //  Iterable<StorageDefinition> contacts = service.findAll(customer, pageable);

        return null; //builder.build(contacts);
    }

    @RequestMapping(value = "/rs/customers/contacts/{contactId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long contactId) {

        Optional<StorageDefinition> resultContact = service.getOne(contactId);
        StorageDefinition storageDefinition = resultContact.orElseThrow(NotFoundException::new);

        return builder.build(storageDefinition);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/customers/{customerId}/contacts", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long customerId, @RequestBody StorageDefinitionResource resource) {

        StorageDefinition storageDefinition = StorageDefinitionBuilder.createContact()
                .withPosition(resource.getPosition())
                .withName(resource.getName())
                .withPhone(resource.getPhone())
                .withEmail(resource.getEmail())
                .withPosition(resource.getPosition()).build();

        Optional<StorageDefinition> contactCreated = null; //service.register(createContactToCustomer(customerId, storageDefinition));

        return builder.build(contactCreated.get());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/customers/contacts/{contactId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResourceItem update(@PathVariable Long contactId, @RequestBody StorageDefinitionResource resource) {

        StorageDefinition storageDefinition = StorageDefinitionBuilder.createContact(contactId)
                .withPosition(resource.getPosition())
                .withName(resource.getName())
                .withPhone(resource.getPhone())
                .withEmail(resource.getEmail())
                .withPosition(resource.getPosition()).build();

        updateFields.addFieldsToUpdate(storageDefinition);
        StorageDefinition storageDefinitionSaved = service.update(storageDefinition);

        return builder.build(storageDefinitionSaved);
    }


}
