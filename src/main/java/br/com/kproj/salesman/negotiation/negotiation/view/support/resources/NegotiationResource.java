package br.com.kproj.salesman.negotiation.negotiation.view.support.resources;


import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Temperature;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

@JsonPropertyOrder({
        "id",
        "deliveryForeCast",
        "introduction",
        "careOf",
        "discount",
        "ammountPayable",
        "temperature",
        "customer",
        "region",
        "seller",
        "links"
})
@ResourceItem(name="negotiations", modelReference = Negotiation.class, parent = CustomerResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NegotiationResource extends Item {

    private Long id;
    private Date deliveryForeCast;
    private String introduction = StringUtils.EMPTY;
    private String careOf = StringUtils.EMPTY;
    private BigDecimal discount;
    private BigDecimal ammountPayable;
    private Temperature temperature;

    private CustomerResource customer;
    private RegionResource region;
    private SellerResource seller;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeliveryForeCast() {
        return deliveryForeCast;
    }

    public void setDeliveryForeCast(Date deliveryForeCast) {
        this.deliveryForeCast = deliveryForeCast;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCareOf() {
        return careOf;
    }

    public void setCareOf(String careOf) {
        this.careOf = careOf;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmmountPayable() {
        return ammountPayable;
    }

    public void setAmmountPayable(BigDecimal ammountPayable) {
        this.ammountPayable = ammountPayable;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Selectable(expression = "customer", externalLink = true)
    public CustomerResource getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResource customer) {
        this.customer = customer;
    }

    @Selectable(expression = "region", externalLink = true)
    public RegionResource getRegion() {
        return region;
    }

    public void setRegion(RegionResource region) {
        this.region = region;
    }

    @Selectable(expression = "seller", externalLink = true)
    public SellerResource getSeller() {
        return seller;
    }

    public void setSeller(SellerResource seller) {
        this.seller = seller;
    }

    public Long getCustomerId() {
        return this.customer == null ? null : this.customer.getId();
    }

    public Long getRegionId() {
        return this.region == null ? null : this.region.getId();
    }

    public Long getSellerId() {
        return this.seller == null ? null : this.seller.getId();
    }
}
