package br.com.kproj.salesman.medias.storage.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinition;
import br.com.kproj.salesman.medias.storage.view.support.resources.StorageDefinitionResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import com.google.common.collect.Lists;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder.getUri;
import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component("contactResourceBuilderAccountsModule")
public class ContactResourceBuilder {


    private HttpServletRequest request;

    @Autowired
    public ContactResourceBuilder(HttpServletRequest request) {
        this.request = request;
    }

    public ResourceItem build(StorageDefinition storageDefinition) {
        StorageDefinitionResource resource = buildItem(storageDefinition);

        return new ResourceItem(resource, getUri(request));
    }

    public ResourceItems build(Iterable<StorageDefinition> contacts) {

        List<StorageDefinitionResource> resources = Lists.newArrayList(contacts).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, getUri(request));
        ResourceHolder.setInfoPageable(contacts, resourceItems);

        return resourceItems;
    }

    public StorageDefinitionResource buildItem(StorageDefinition storageDefinition) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        StorageDefinitionResource resource = new StorageDefinitionResource();

        ConverterToResource.convert(storageDefinition, resource, context);
        return resource;
    }

}
