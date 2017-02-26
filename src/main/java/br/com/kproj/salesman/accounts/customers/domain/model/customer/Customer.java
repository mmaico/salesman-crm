package br.com.kproj.salesman.accounts.customers.domain.model.customer;

import br.com.kproj.salesman.accounts.customers.domain.model.address.Address;
import br.com.kproj.salesman.accounts.customers.domain.model.contact.Contact;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.google.common.collect.Lists;

import javax.validation.constraints.NotNull;
import java.util.List;


public class Customer extends ModelIdentifiable {

    private Long id;

    @NotNull(message = "account.name.is.invalid")
    private String name;
    private String site;
    private String description;
    private List<Address> addresses = Lists.newArrayList();
    private List<Contact> contacts = Lists.newArrayList();

    @Override
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
