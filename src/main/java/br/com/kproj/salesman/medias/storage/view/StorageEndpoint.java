package br.com.kproj.salesman.medias.storage.view;


import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
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

import java.util.Optional;


@RestController
public class StorageEndpoint {

    private StorageFacade service;

    private StorageResourceBuilder builder;

    @Autowired
    public StorageEndpoint(StorageFacade service, StorageResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/storages", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PageableDefault(size = 100) Pageable pageable) {
        Iterable<Storage> storages = service.findAll(pageable);

        return builder.build(storages);
    }

    @RequestMapping(value = "/rs/storages/{storageId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long storageId) {

        Optional<Storage> result = service.getOne(storageId);
        Storage storageDefinition = result.orElseThrow(NotFoundException::new);

        return builder.build(storageDefinition);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/storages", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@RequestBody StorageResource resource) {

        Storage storageDefinition = StorageBuilder.createStorage()
                .withName(resource.getName()).build();

        Optional<Storage> contactCreated = service.register(storageDefinition);

        return builder.build(contactCreated.get());
    }



}
