package br.com.kproj.salesman.accounts.addresses.domain.model.address;

import br.com.kproj.salesman.accounts.addresses.domain.model.customer.Customer;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;


public class Address extends ModelIdentifiable {

    public enum Type {
        BUSINESS("Comercial"), BILLING("Cobrança"), UNINFORMED("NaoInformado");

        private String name;
        Type(String name) {
            this.name = name;
        }

        public String getName()  {
            return this.name;
        }

        public static Type getByName(String name) {

            for (Type type : values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }

            return UNINFORMED;
        }
    }

    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private Customer customer;

    private Type type = Type.UNINFORMED;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
