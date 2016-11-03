package br.com.kproj.salesman.products_catalog.catalog.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.Represent;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.catalog.view.support.resources.SaleableResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.apiconverter.resources.Link;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class SaleableResourceBuilder {

    private static Map<Represent, SelectLink> selectLink = new HashMap<>();

    static {
        selectLink.put(Represent.PRODUCT, (saleableUnit ->
            Link.createLink("products", "/rs/saleables/products/" + saleableUnit.getId())
        ));

        selectLink.put(Represent.SERVICE, (saleableUnit ->
                Link.createLink("services", "/rs/saleables/services/" + saleableUnit.getId())
        ));

        selectLink.put(Represent.PACKAGE, (saleableUnit ->
                Link.createLink("packages", "/rs/saleables/packages/" + saleableUnit.getId())
        ));

        selectLink.put(Represent.NO_REPRESENT, (saleableUnit -> null));
    }


    public ResourceItem build(SaleableUnit saleableUnit, String uri) {
        SaleableResource resource = buildItem(saleableUnit);

        return new ResourceItem(resource, uri);
    }

    public ResourceItems build(Collection<SaleableUnit> saleables, String uri) {
        List<SaleableResource> resources = saleables.stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, uri);
    }

    public SaleableResource buildItem(SaleableUnit saleableUnit) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        Link linkSpecialization = selectLink.get(saleableUnit.getRepresent()).select(saleableUnit);
        context.addLinkConf(SaleableResource.class, linkSpecialization);

        SaleableResource resource = new SaleableResource();

        ConverterToResource.convert(saleableUnit, resource, context);
        return resource;
    }

    private interface SelectLink {

        Link select(SaleableUnit saleableUnit);
    }
}
