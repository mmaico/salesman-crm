package br.com.kproj.salesman.medias.media.view.support.builders;



import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.medias.media.domain.media.FileContent;
import br.com.kproj.salesman.medias.media.view.support.resources.FileContentResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


import static br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder.getUri;
import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class ImageResourceBuilder {

    @Autowired
    private HttpServletRequest request;

    public ResourceItem build(FileContent fileContent) {
        FileContentResource resource = buildItem(fileContent);

        return new ResourceItem(resource, getUri(request));
    }


    private FileContentResource buildItem(FileContent fileContent) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        FileContentResource resource = new FileContentResource();
        ConverterToResource.convert(fileContent, resource, context);

        resource.setUrl(fileContent.getCdnUrl());


        return resource;
    }


}
