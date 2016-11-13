package br.com.kproj.salesman.products_catalog.delivery_definition.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.Checklist;
import br.com.kproj.salesman.products_catalog.delivery_definition.view.support.resources.ChecklistResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class ChecklistResourceBuilder {


    public ResourceItem build(Checklist checklist, String uri) {
        ChecklistResource resource = buildItem(checklist);

        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Collection<Checklist> checklists, String uri) {
        List<ChecklistResource> resources = checklists.stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, uri);
    }

    public ChecklistResource buildItem(Checklist checklist) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        ChecklistResource resource = new ChecklistResource();

        ConverterToResource.convert(checklist, resource, context);
        return resource;
    }

}
