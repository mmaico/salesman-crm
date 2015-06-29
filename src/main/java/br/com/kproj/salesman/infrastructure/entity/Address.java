package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Entity;

@Entity
public class Address extends Identifiable {

    private final String code;
    private final String street;
    private final String complement;
    private final String number;

    public Address() {
        super();
        code = null;
        street = null;
        complement = null;
        number = null;
    }

    public Address(Long id) {
        super(id);
        code = null;
        street = null;
        complement = null;
        number = null;
    }

    public Address(String code) {
        super();
        this.code = code;
        street = null;
        complement = null;
        number = null;
    }

    public Address(String code, String street, String number) {
        this.code = code;
        this.street = street;
        this.number = number;
        complement = null;
    }

    public Address(String code, String street, String number, String complement) {
        this.code = code;
        this.street = street;
        this.number = number;
        this.complement = complement;
    }

    public String getCode() {
        return code;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("number='").append(number).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", complement='").append(complement).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
