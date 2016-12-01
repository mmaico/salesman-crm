package br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.resources;


import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleablePackage;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="packages", modelReference = SaleablePackage.class, parent = SaleableResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageResource extends Item {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
