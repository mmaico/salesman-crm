package br.com.kproj.salesman.medias.media.view;


import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.medias.media.application.MediaFacade;
import br.com.kproj.salesman.medias.media.domain.media.FileContent;
import br.com.kproj.salesman.medias.media.domain.media.MediaInStorage;
import br.com.kproj.salesman.medias.media.view.support.builders.ImageResourceBuilder;
import br.com.kproj.salesman.medias.media.view.support.resources.RawImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.com.kproj.salesman.medias.media.domain.media.FileContentRaw.createRaw;
import static br.com.kproj.salesman.medias.media.domain.storage.Storage.createStorage;


@RestController
public class MediaEndpoint {


    private MediaFacade service;
    private ImageResourceBuilder builder;

    @Autowired
    public MediaEndpoint(MediaFacade service, ImageResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    ///rs/storages/name=storage-name/medias
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/storages/{name}/medias", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@RequestBody RawImage resource, @MatrixVariable String name) {

        MediaInStorage mediaInStorage = MediaInStorage.create(createRaw(resource.getImage()), createStorage(name));
        FileContent fileContent = service.store(mediaInStorage);

        return builder.build(fileContent);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/storages/medias/{mediaId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long mediaId) {

        Optional<FileContent> result = service.findOne(mediaId);
        FileContent fileContent = result.orElseThrow(NotFoundException::new);

        return builder.build(fileContent);
    }
}
