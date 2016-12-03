package br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItem;
import br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.resources.SaleableItemResource;
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

@Component("saleableItemResourceBuilderNegotiatedModule")
public class SaleableItemResourceBuilder {

    @Autowired
    private HttpServletRequest request;


    public ResourceItem build(SaleableItem saleableItem) {
        SaleableItemResource resource = buildItem(saleableItem);

        return new ResourceItem(resource, getUri(request));
    }

    public ResourceItems build(Iterable<SaleableItem> items) {

        List<SaleableItemResource> resources = Lists.newArrayList(items).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, getUri(request));
        ResourceHolder.setInfoPageable(items, resourceItems);

        return resourceItems;
    }

    public SaleableItemResource buildItem(SaleableItem saleableItem) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        SaleableItemResource resource = new SaleableItemResource();

        ConverterToResource.convert(saleableItem, resource, context);
        return resource;
    }

}
