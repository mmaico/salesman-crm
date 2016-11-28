package br.com.kproj.salesman.accounts.contacts.view.support.resources;


import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name",
        "email",
        "phone",
        "position",
        "customer",
        "links"
})
@ResourceItem(name="contacts", modelReference = Contact.class, parent = CustomerResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactResource extends Item {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String position;
    private CustomerResource customer;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Selectable(expression = "of-customer", externalLink = true)
    public CustomerResource getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResource customer) {
        this.customer = customer;
    }

    @JsonIgnore
    public Long getCustomerId() {
        return this.customer == null ? null : this.customer.getId();
    }
}
