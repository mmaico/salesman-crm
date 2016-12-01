package br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.resources;


import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "price",
        "originalPrice",
        "quantity",
        "links"
})
@ResourceItem(name="negotiated-items", modelReference = Negotiated.class, parent = NegotiationResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NegotiatedResource extends Item {

    private Long id;

    private BigDecimal price = BigDecimal.ZERO;

    private BigDecimal originalPrice;

    private Integer quantity = 0;

    private NegotiationResource negotiation;

    private List<SaleableItemResource> saleableItems;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Selectable(expression = "negotiation", externalLink = true)
    public NegotiationResource getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(NegotiationResource negotiation) {
        this.negotiation = negotiation;
    }

    @Selectable(expression = "saleables-items", externalLink = true)
    public List<SaleableItemResource> getSaleableItems() {
        return saleableItems;
    }

    public void setSaleableItems(List<SaleableItemResource> saleableItems) {
        this.saleableItems = saleableItems;
    }
}
