package br.com.kproj.salesman.accounts.addresses.view.support.builders;


import br.com.kproj.salesman.accounts.addresses.domain.model.address.Address;
import br.com.kproj.salesman.accounts.addresses.view.support.resources.AddressResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
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

@Component("addressResourceBuilderAccountsModule")
public class AddressResourceBuilder {


    private HttpServletRequest request;

    @Autowired
    public AddressResourceBuilder(HttpServletRequest request) {
        this.request = request;
    }

    public ResourceItem build(Address address) {
        AddressResource resource = buildItem(address);

        return new ResourceItem(resource, getUri(request));
    }

    public ResourceItems build(Iterable<Address> addresses) {

        List<AddressResource> resources = Lists.newArrayList(addresses).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, ResourceHolder.getUri(request));
        ResourceHolder.setInfoPageable(addresses, resourceItems);

        return resourceItems;
    }

    public AddressResource buildItem(Address address) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        AddressResource resource = new AddressResource();

        ConverterToResource.convert(address, resource, context);
        return resource;
    }

}
