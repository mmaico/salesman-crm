package br.com.kproj.salesman.medias.storage.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.medias.storage.domain.model.definition.Storage;
import br.com.kproj.salesman.medias.storage.view.support.resources.StorageResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder.getUri;
import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class StorageResourceBuilder {


    private HttpServletRequest request;

    @Autowired
    public StorageResourceBuilder(HttpServletRequest request) {
        this.request = request;
    }

    public ResourceItem build(Storage storage) {
        StorageResource resource = buildItem(storage);

        return new ResourceItem(resource, getUri(request));
    }

    public ResourceItems build(Iterable<Storage> contacts) {

        List<StorageResource> resources = Lists.newArrayList(contacts).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, getUri(request));
        ResourceHolder.setInfoPageable(contacts, resourceItems);

        return resourceItems;
    }

    public StorageResource buildItem(Storage storage) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        StorageResource resource = new StorageResource();

        ConverterToResource.convert(storage, resource, context);
        return resource;
    }

}
