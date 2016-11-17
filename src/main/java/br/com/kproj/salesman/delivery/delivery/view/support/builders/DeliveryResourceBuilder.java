package br.com.kproj.salesman.delivery.delivery.view.support.builders;


import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.delivery.delivery.view.support.resources.DeliveryResource;
import br.com.kproj.salesman.delivery.delivery.view.support.resources.WorkerResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class DeliveryResourceBuilder {


    public ResourceItem build(Delivery delivery, String uri) {
        DeliveryResource resource = buildItem(delivery);

        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Iterable<Delivery> deliveries, String uri) {

        List<DeliveryResource> resources = Lists.newArrayList(deliveries).stream()
                .map(item -> buildItem(item))
                .collect(Collectors.toList());

        if (deliveries instanceof Page) {
            long totalElements = ((Page<Delivery>) deliveries).getTotalElements();
            int size = ((Page<Delivery>) deliveries).getSize();
            return new ResourceItems(resources, uri, totalElements, size);
        } else {
            return new ResourceItems(resources, uri);
        }
    }

    public DeliveryResource buildItem(Delivery delivery) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        DeliveryResource resource = new DeliveryResource();
        ConverterToResource.convert(delivery, resource, context);

        return resource;
    }

}
