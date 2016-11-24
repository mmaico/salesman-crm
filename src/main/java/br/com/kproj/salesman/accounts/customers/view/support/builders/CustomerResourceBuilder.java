package br.com.kproj.salesman.accounts.customers.view.support.builders;


import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.customers.view.support.resources.CustomerResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import com.google.common.collect.Lists;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component("customerResourceBuilderDeliveryModule")
public class CustomerResourceBuilder {

    @Autowired
    private HttpServletRequest request;


    public ResourceItem build(Customer customer) {
        CustomerResource resource = buildItem(customer);

        return new ResourceItem(resource, request.getRequestURI());
    }

    public ResourceItems build(Iterable<Customer> customers) {

        List<CustomerResource> resources = Lists.newArrayList(customers).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, request.getRequestURI());
    }

    public CustomerResource buildItem(Customer customer) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        CustomerResource resource = new CustomerResource();

        ConverterToResource.convert(customer, resource, context);
        return resource;
    }

}
