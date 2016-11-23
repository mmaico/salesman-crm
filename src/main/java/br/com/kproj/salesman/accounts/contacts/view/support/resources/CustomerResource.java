package br.com.kproj.salesman.accounts.contacts.view.support.resources;


import br.com.kproj.salesman.accounts.contacts.domain.model.customer.Customer;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="customers", modelReference = Customer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerResource extends Item {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
