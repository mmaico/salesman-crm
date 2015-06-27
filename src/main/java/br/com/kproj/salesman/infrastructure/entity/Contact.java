package br.com.kproj.salesman.infrastructure.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Contact extends Identifiable {

    @NotNull @Size(min = 2, max = 30)
    private final String name;

    @Email
    private final String email;

    private final String phone;

    public Contact(String name, String email, String phone) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

}
