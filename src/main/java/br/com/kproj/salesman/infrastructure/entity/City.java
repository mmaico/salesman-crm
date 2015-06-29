package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Entity;

@Entity
public class City extends Identifiable {

    private final String name;

    public City(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
