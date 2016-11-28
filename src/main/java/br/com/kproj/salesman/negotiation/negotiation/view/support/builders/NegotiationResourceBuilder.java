package br.com.kproj.salesman.negotiation.negotiation.view.support.builders;



import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.negotiation.view.support.resources.NegotiationResource;
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

@Component("negotiationResourceBuilderNegotiationModule")
public class NegotiationResourceBuilder {

    @Autowired
    private HttpServletRequest request;


    public ResourceItem build(Negotiation negotiation) {
        NegotiationResource resource = buildItem(negotiation);

        return new ResourceItem(resource, getUri(request));
    }

    public ResourceItems build(Iterable<Negotiation> negotiations) {

        List<NegotiationResource> resources = Lists.newArrayList(negotiations).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, getUri(request));
        ResourceHolder.setInfoPageable(negotiations, resourceItems);

        return resourceItems;
    }

    public NegotiationResource buildItem(Negotiation negotiation) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        NegotiationResource resource = new NegotiationResource();

        ConverterToResource.convert(negotiation, resource, context);
        return resource;
    }

}
