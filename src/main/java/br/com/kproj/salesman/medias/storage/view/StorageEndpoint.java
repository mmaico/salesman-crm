package br.com.kproj.salesman.medias.storage.view;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.medias.storage.application.StorageFacade;
import br.com.kproj.salesman.medias.storage.domain.model.definition.Storage;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageBuilder;
import br.com.kproj.salesman.medias.storage.view.support.builders.StorageResourceBuilder;
import br.com.kproj.salesman.medias.storage.view.support.resources.StorageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.helpers.RequestBody.body;
import static br.com.kproj.salesman.infrastructure.model.ModelIdentifiable.When.when;


@RestController
public class StorageEndpoint {

    private StorageFacade service;

    private StorageResourceBuilder builder;

    @Autowired
    public StorageEndpoint(StorageFacade service, StorageResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    //@RequestMapping(value = "/rs/customders/{customerId}/contacts", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PathVariable Long customerId, @PageableDefault(size = 100) Pageable pageable) {
    //    Customer customer = new Customer(customerId);

      //  Iterable<Storage> contacts = service.findAll(customer, pageable);

        return null; //builder.build(contacts);
    }

//    @RequestMapping(value = "/rs/customers/contacts/{contactId}", method = RequestMethod.GET)
//    public @ResponseBody
//    ResourceItem findOne(@PathVariable Long contactId) {
//
//        Optional<Storage> resultContact = service.getOne(contactId);
//        Storage storageDefinition = resultContact.orElseThrow(NotFoundException::new);
//
//        return builder.build(storageDefinition);
//    }
//
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value = "/rs/customers/{customerId}/contacts", method = RequestMethod.POST)
//    public @ResponseBody
//    ResourceItem create(@PathVariable Long customerId, @RequestBody StorageResource resource) {
//
//        Storage storageDefinition = StorageBuilder.createContact()
//                .withPosition(resource.getPosition())
//                .withName(resource.getName())
//                .withPhone(resource.getPhone())
//                .withEmail(resource.getEmail())
//                .withPosition(resource.getPosition()).build();
//
//        Optional<Storage> contactCreated = null; //service.register(createContactToCustomer(customerId, storageDefinition));
//
//        return builder.build(contactCreated.get());
//    }
//
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/storage/{storageId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResourceItem update(@PathVariable Long storageId, @RequestBody StorageResource resource) {

        Storage storage = StorageBuilder.createStorage(storageId)
                .withName(resource.getName()).build();

        Storage storageDefinitionSaved = service.update(storage);

        return builder.build(storageDefinitionSaved);
    }


}
