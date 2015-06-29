package br.com.kproj.salesman.infrastructure.entity;


import javax.persistence.Entity;

@Entity
public class Country extends Identifiable {

    private final String name;
    private final String code;

    public Country(String name, String code) {
        super();
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    
}
