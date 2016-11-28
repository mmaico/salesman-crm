package br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.customer.Customer;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.operation.Region;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.Seller;
import com.trex.shared.annotations.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;

@Model
public class Negotiation extends ModelIdentifiable {

    private Long id;

    private Customer customer;

    private Seller seller;

    private Region region;

    @DateTimeFormat(pattern = "dd/M/Y")
    private Date deliveryForeCast;

    private String introduction = StringUtils.EMPTY;

    private String careOf = StringUtils.EMPTY;

    private BigDecimal discount;

    private BigDecimal ammountPayable;

    private Temperature temperature;

    @Autowired
    private NegotiationRepository repository;

    public Negotiation() {
        autowire(this);
    }

    public boolean temperatureWasClosedWon() {
        Optional<Negotiation> negotiation = repository.findOne(this.getId());
        return Temperature.CLOSED_WON.equals(negotiation.get().getTemperature());
    }

    public void changeTemperatureFor(Temperature newTemperature) {
        Optional<Negotiation> negotiation = repository.findOne(this.getId());
        negotiation.get().setTemperature(newTemperature);
        repository.save(negotiation.get());
    }

    //getters and setters

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setSellerOnlyHaveId(Seller seller) {
        if (seller != null && !seller.isNew()) {
            this.seller = seller;
        }
    }

    public String getCareOf() {
        return careOf;
    }

    public void setCareOf(String careOf) {
        this.careOf = careOf;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
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
}
