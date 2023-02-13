package br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.resources.NegotiatedResource;
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

@Component("negotiatedResourceBuilderNegotiatedModule")
public class NegotiatedResourceBuilder {

    @Autowired
    private HttpServletRequest request;


    public ResourceItem build(Negotiated negotiated) {
        NegotiatedResource resource = buildItem(negotiated);

        return new ResourceItem(resource, getUri(request));
    }

    public ResourceItems build(Iterable<Negotiated> negotiateds) {

        List<NegotiatedResource> resources = Lists.newArrayList(negotiateds).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, getUri(request));
        ResourceHolder.setInfoPageable(negotiateds, resourceItems);

        return resourceItems;
    }

    public NegotiatedResource buildItem(Negotiated negotiated) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        NegotiatedResource resource = new NegotiatedResource();

        ConverterToResource.convert(negotiated, resource, context);
        return resource;
    }

}
