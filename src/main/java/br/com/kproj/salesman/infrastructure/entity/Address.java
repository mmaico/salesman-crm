package br.com.kproj.salesman.infrastructure.entity;

import br.com.kproj.salesman.infrastructure.entity.location.City;
import br.com.kproj.salesman.infrastructure.entity.location.Country;
import br.com.kproj.salesman.infrastructure.entity.location.State;
import br.com.kproj.salesman.infrastructure.entity.person.Person;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="address")
public class Address extends Identifiable {

	private static final long serialVersionUID = 2011235325379126884L;
    public enum Type {
        BUSINESS, BILLING
    }
	private String postalCode;
    private String street;
    private String complement;
    private String number;
    private String district;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="country_id")
    private Country country;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="state_id")
    private State state;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="city_id")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    public Address() {}

    public Address(Long id) {
        super(id);
        postalCode = null;
        street = null;
        complement = null;
        number = null;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public String getNumber() {
        return number;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("number='").append(number).append('\'');
        sb.append(", code='").append(postalCode).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", complement='").append(complement).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
