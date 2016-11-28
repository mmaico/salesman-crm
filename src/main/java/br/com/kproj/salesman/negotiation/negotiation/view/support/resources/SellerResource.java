package br.com.kproj.salesman.negotiation.negotiation.view.support.resources;


import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.Seller;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="users", modelReference = Seller.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerResource extends Item {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
