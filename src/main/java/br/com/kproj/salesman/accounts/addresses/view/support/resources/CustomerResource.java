package br.com.kproj.salesman.accounts.addresses.view.support.resources;



import br.com.kproj.salesman.accounts.addresses.domain.model.customer.Customer;
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
