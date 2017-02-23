package br.com.kproj.salesman.medias.media.view;


import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.medias.media.application.FileContentFacade;
import br.com.kproj.salesman.medias.media.domain.FileContent;
import br.com.kproj.salesman.medias.media.domain.FileContentRaw;
import br.com.kproj.salesman.medias.media.view.support.builders.ImageResourceBuilder;
import br.com.kproj.salesman.medias.media.view.support.resources.RawImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class FileContentEndpoint {


    private FileContentFacade service;
    private ImageResourceBuilder builder;

    @Autowired
    public FileContentEndpoint(FileContentFacade service, ImageResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/api/media-images", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@RequestBody RawImage resource) {

        FileContentRaw raw = FileContentRaw.createRaw(resource.getImage());

        FileContent fileContent = service.store(raw);

        return builder.build(fileContent);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/media-images/{mediaImageId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long mediaImageId) {

        Optional<FileContent> result = service.findOne(mediaImageId) ;
        FileContent fileContent = result.orElseThrow(NotFoundException::new);

        return builder.build(fileContent);
    }
}
