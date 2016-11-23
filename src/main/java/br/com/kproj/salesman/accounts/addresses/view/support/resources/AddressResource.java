package br.com.kproj.salesman.accounts.addresses.view.support.resources;



import br.com.kproj.salesman.accounts.addresses.domain.model.address.Address;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "street",
        "city",
        "state",
        "zipCode",
        "country",
        "type",
        "customer",
        "links"
})
@ResourceItem(name="contacts", modelReference = Address.class, parent = CustomerResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressResource extends Item {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private Address.Type type = Address.Type.UNINFORMED;

    private CustomerResource customer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Address.Type getType() {
        return type;
    }

    public void setType(Address.Type type) {
        this.type = type;
    }

    @Selectable(expression = "of-customer", externalLink = true)
    public CustomerResource getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResource customer) {
        this.customer = customer;
    }


}
