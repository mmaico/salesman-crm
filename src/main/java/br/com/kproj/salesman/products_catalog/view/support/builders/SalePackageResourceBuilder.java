package br.com.kproj.salesman.products_catalog.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.domain.model.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.view.support.resources.SalePackageResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SalePackageResourceBuilder {

    public ResourceItem build(SalePackage salePackage, String uri) {
        SalePackageResource resource = new SalePackageResource();
        ConverterToResource.convert(salePackage, resource);
        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Collection<SalePackage> salePackages, String uri) {
        List<SalePackageResource> resources = salePackages.stream().map(item -> {
            SalePackageResource resource = new SalePackageResource();
            ConverterToResource.convert(item, resource);
            return resource;
        }).collect(Collectors.toList());

        return new ResourceItems(resources, uri);
    }

}
