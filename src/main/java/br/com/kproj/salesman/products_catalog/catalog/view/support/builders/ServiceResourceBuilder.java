package br.com.kproj.salesman.products_catalog.catalog.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.services.Service;
import br.com.kproj.salesman.products_catalog.catalog.view.support.resources.ServiceResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceResourceBuilder {


    public ResourceItem build(Service service, String uri) {
        ServiceResource resource = new ServiceResource();
        ConverterToResource.convert(service, resource);
        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Collection<Service> services, String uri) {
        List<ServiceResource> resources = services.stream().map(item -> {
            ServiceResource resource = new ServiceResource();
            ConverterToResource.convert(item, resource);
            return resource;
        }).collect(Collectors.toList());

        return new ResourceItems(resources, uri);
    }

}
