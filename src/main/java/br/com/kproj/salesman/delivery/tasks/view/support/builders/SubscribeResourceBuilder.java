package br.com.kproj.salesman.delivery.tasks.view.support.builders;


import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.kproj.salesman.delivery.tasks.view.support.resources.SubscribeResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class SubscribeResourceBuilder {



    public ResourceItem build(Subscriber subscriber, String uri) {
        SubscribeResource resource = buildItem(subscriber);

        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Iterable<Subscriber> subscribes, String uri) {

        List<SubscribeResource> resources = Lists.newArrayList(subscribes).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, uri);
    }

    public SubscribeResource buildItem(Subscriber subscriber) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        SubscribeResource resource = new SubscribeResource();

        ConverterToResource.convert(subscriber, resource, context);
        return resource;
    }

}
