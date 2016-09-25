package br.com.kproj.salesman.sales.domain.model.sales;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.sales.domain.model.account.Customer;
import br.com.kproj.salesman.sales.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.sales.domain.model.operation.Region;
import br.com.kproj.salesman.sales.domain.model.payments.Installments;
import br.com.kproj.salesman.sales.domain.model.seller.Seller;
import com.trex.shared.annotations.Model;

import java.util.Date;


@Model
public class SalesOrder extends ModelIdentifiable {

    private Long id;

    private Date deliveryForecast;

    private Date creation;

    private Products products;

    private Installments installments;

    private Seller seller;

    private Customer customer;

    private Negotiation negotiation;

    private Region region;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeliveryForecast() {
        return deliveryForecast;
    }

    public void setDeliveryForecast(Date deliveryForecast) {
        this.deliveryForecast = deliveryForecast;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Installments getInstallments() {
        return installments;
    }

    public void setInstallments(Installments installments) {
        this.installments = installments;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
