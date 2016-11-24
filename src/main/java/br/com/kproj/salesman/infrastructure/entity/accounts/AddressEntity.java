package br.com.kproj.salesman.infrastructure.entity.accounts;

import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="addresses")
public class AddressEntity extends Identifiable {

    public enum Type {
        BUSINESS("Comercial"), BILLING("Cobrança");

        private String name;
        Type(String name) {
            this.name = name;
        }

        public String getName()  {
            return this.name;
        }
    }

    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @ExcludeField
    private CustomerEntity customer;

    @Override
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
