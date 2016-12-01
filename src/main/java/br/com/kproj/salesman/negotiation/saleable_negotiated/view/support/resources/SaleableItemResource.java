package br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.resources;


import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItem;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="saleables-items", modelReference = SaleableItem.class, parent = NegotiatedResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleableItemResource extends Item {

    private Long id;

    private SaleableResource saleable;

    private PackageResource usedPackage;

    private NegotiatedResource negotiated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Selectable(expression = "saleable", externalLink = true)
    public SaleableResource getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableResource saleable) {
        this.saleable = saleable;
    }

    @Selectable(expression = "used-package", externalLink = true)
    public PackageResource getUsedPackage() {
        return usedPackage;
    }

    public void setUsedPackage(PackageResource usedPackage) {
        this.usedPackage = usedPackage;
    }

    @Selectable(expression = "negotiated", externalLink = true)
    public NegotiatedResource getNegotiated() {
        return negotiated;
    }

    public void setNegotiated(NegotiatedResource negotiated) {
        this.negotiated = negotiated;
    }
}
