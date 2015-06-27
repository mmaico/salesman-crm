package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Entity;

@Entity
public class City extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
