package br.com.kproj.salesman.products_catalog.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.products.Product;
import br.com.kproj.salesman.products_catalog.view.support.resources.ProductResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductResourceBuilder {


    public ResourceItem build(Product product, String uri) {
        ProductResource resource = new ProductResource();
        ConverterToResource.convert(product, resource);
        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Collection<Product> products, String uri) {
        List<ProductResource> resources = products.stream().map(item -> {
            ProductResource resource = new ProductResource();
            ConverterToResource.convert(item, resource);
            return resource;
        }).collect(Collectors.toList());

        return new ResourceItems(resources, uri);
    }

}
