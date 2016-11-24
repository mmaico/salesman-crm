package br.com.kproj.salesman.accounts.customers.view.support.resources;


import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collection;

@JsonPropertyOrder({
        "id",
        "name",
        "site",
        "description",
        "addresses",
        "contacts",
        "links"
})
@ResourceItem(name="customers", modelReference = Customer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerResource extends Item {

    private Long id;
    private String name;
    private String site;
    private String description;

    private Collection<AddressResource> addresses;
    private Collection<ContactResource> contacts;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Selectable(expression = "has-addresses")
    public Collection<AddressResource> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<AddressResource> addresses) {
        this.addresses = addresses;
    }

    @Selectable(expression = "has-contacts")
    public Collection<ContactResource> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<ContactResource> contacts) {
        this.contacts = contacts;
    }
}
