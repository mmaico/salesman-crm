package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address extends Identifiable {

	private static final long serialVersionUID = 2011235325379126884L;
	private String postalCode;
    private String street;
    private String complement;
    private String number;
    private String district;

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
