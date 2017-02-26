package br.com.kproj.salesman.products_catalog.catalog.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.catalog.view.support.resources.SalePackageResource;
import br.com.kproj.salesman.products_catalog.catalog.view.support.resources.SaleableRelationResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.apiconverter.configs.LinkRemoveConfig;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class SalePackageResourceBuilder {

    @Autowired
    private SaleableResourceBuilder saleableBuilder;

    public ResourceItem build(SalePackage salePackage, String uri) {
        SalePackageResource resource = buildItem(salePackage);

        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Collection<SalePackage> salePackages, String uri) {
        List<SalePackageResource> resources = salePackages.stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, uri);
    }

    private SalePackageResource buildItem(SalePackage salePackage) {
        SalePackageResource resource = new SalePackageResource();

        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);
        context.addLinkConf(SalePackageResource.class, LinkRemoveConfig.createLinkRemoveConfig("has-relations"));

        ConverterToResource.convert(salePackage, resource, context);

        List<SaleableRelationResource> relations = salePackage.getRelations().stream().map(relation -> {
            SaleableRelationResource saleableRelation = new SaleableRelationResource();
            saleableRelation.setId(relation.getId());
            saleableRelation.setSaleable(saleableBuilder.buildItem(relation.getSaleable()));
            return saleableRelation;
        }).collect(Collectors.toList());

        resource.setRelations(relations);
        return resource;
    }

}
