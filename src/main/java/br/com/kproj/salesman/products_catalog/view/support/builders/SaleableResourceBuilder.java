package br.com.kproj.salesman.products_catalog.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.view.support.resources.SaleableResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleableResourceBuilder {


    public ResourceItem build(SaleableUnit saleableUnit, String uri) {
        SaleableResource resource = new SaleableResource();
        ConverterToResource.convert(saleableUnit, resource);
        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Collection<SaleableUnit> saleables, String uri) {
        List<SaleableResource> resources = saleables.stream().map(item -> {
            SaleableResource resource = new SaleableResource();

            ConverterToResource.convert(item, resource);
            return resource;
        }).collect(Collectors.toList());

        return new ResourceItems(resources, uri);
    }

}
