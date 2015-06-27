package br.com.kproj.salesman.infrastructure.entity;


import javax.persistence.Entity;

@Entity
public class Country extends AbstractEntity {

    private String name;
    private String bacenCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBacenCode() {
        return bacenCode;
    }

    public void setBacenCode(String bacenCode) {
        this.bacenCode = bacenCode;
    }
}
