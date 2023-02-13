package br.com.kproj.salesman.accounts.contacts.view.support.builders;



import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.view.support.resources.ContactResource;
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

@Component("contactResourceBuilderAccountsModule")
public class ContactResourceBuilder {


    private HttpServletRequest request;

    @Autowired
    public ContactResourceBuilder(HttpServletRequest request) {
        this.request = request;
    }

    public ResourceItem build(Contact contact) {
        ContactResource resource = buildItem(contact);

        return new ResourceItem(resource, getUri(request));
    }

    public ResourceItems build(Iterable<Contact> contacts) {

        List<ContactResource> resources = Lists.newArrayList(contacts).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, getUri(request));
        ResourceHolder.setInfoPageable(contacts, resourceItems);

        return resourceItems;
    }

    public ContactResource buildItem(Contact contact) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        ContactResource resource = new ContactResource();

        ConverterToResource.convert(contact, resource, context);
        return resource;
    }

}
